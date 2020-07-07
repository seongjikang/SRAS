package com.shinhan.srasCrawler.serviceImpl;

import com.shinhan.srasCrawler.dao.CrawlingDao;
import com.shinhan.srasCrawler.model.CrawlingReview;
import com.shinhan.srasCrawler.service.CrawlingService;
import okhttp3.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CrawlingServiceImpl implements CrawlingService {
    @Autowired
    CrawlingDao crawlingDao;

    private WebDriver driver;

    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";

    //Tech Local Pc
    private static final String WEB_DRIVER_PATH = "D:/home/srasdomain/srasApp/webdriver/chromedriver.exe";

    //Kang Local Pc
    //private static final String  WEB_DRIVER_PATH = "C:/dev/crawling/chromedriver.exe";

    //Server
    //private static final String WEB_DRIVER_PATH = "/home/package/chrome/chromedriver";

    private static final int WAIT_TIME = 10000;

    private static final String ANDROID_FAKE_URL = "https://accounts.google.com/signin/oauth/identifier?client_id=717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com&scope=profile%20email&redirect_uri=https%3A%2F%2Fstackauth.com%2Fauth%2Foauth2%2Fgoogle&state=%7B%22sid%22%3A1%2C%22st%22%3A%2259%3A3%3ABBC%2C16%3A455f0a4a65ba15b3%2C10%3A1588148151%2C16%3Accef4f5f4af21523%2C66eb465c75adb02ec3ecab9462f5a347deef93df04b0cad5ff2ad5046a9ac538%22%2C%22cdl%22%3Anull%2C%22cid%22%3A%22717762328687-iludtf96g1hinl76e4lc1b9a82g457nn.apps.googleusercontent.com%22%2C%22k%22%3A%22Google%22%2C%22ses%22%3A%2263880cf5f7cc4027973f98e8f96c9d61%22%7D&response_type=code&o2v=1&as=3UdazpAX9mkbSdiWnD8B2g&flowName=GeneralOAuthFlow";
    private static final String ANDROID_BASE_URL = "https://play.google.com/apps/publish/?account=5722814114791747414#ReviewsPlace:p=com.shinhan.sbanking&appid=4975939118589000144";

    private static final String ANDROID_NO_LOGIN_URL = "https://play.google.com/store/apps/details?id=com.shinhan.sbanking&showAllReviews=true&hl=kr";

    private static final String IOS_BASE_URL = "https://appstoreconnect.apple.com/WebObjects/iTunesConnect.woa/ra/ng/app/357484932/ios/ratingsResponses";

    private static final String ANDROID_LOGIN_NEXT_BTN_TAG = "//*[@id='identifierNext']";
    private static final String ANDROID_LOGIN_OK_BTN_TAG = "//*[@id='passwordNext']";

    private static final String ANDROID_NO_LOGIN_REVIEW_LIST_TAG = "//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/div/div";
    private static final String ANDROID_SELECT_TAB = "//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/c-wiz/div[1]/div/div[1]/div[2]/span";
    private static final String ANDROID_RECENTLY = "//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/c-wiz/div[1]/div/div[2]/div[1]";

    private static final String IOS_REVIEW_FRAME_TAG = "aid-auth-widget-iFrame";
    private static final String IOS_REVIEW_LIST_TAG = "//*[@id='infinite-scroll']/div";
    private static final String IOS_LOGIN_NEXT_BTN_TAG = "//*[@id='sign-in']";
    private static final String IOS_LOGIN_OK_BTN_TAG = "#sign-in";

    private String userId;
    private String userPw;

    @Override
    public Map<String, Object> getAndroidReviews(String reviewDate) {
        Map<String, Object> account = crawlingDao.selectPassword("android");

        init(account.get("userId").toString(), account.get("userPw").toString());
        return crawl("android", reviewDate);
    }

    @Override
    public Map<String, Object> getIosReviews(String reviewDate) {
        Map<String, Object> account = crawlingDao.selectPassword("ios");

        init(account.get("userId").toString(), account.get("userPw").toString());
        return crawl("ios", reviewDate);
    }

    public ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920x1080");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--lang=ko_KR");
        options.addArguments("--disable-gpu");
        options.addArguments("--incognito");
        options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36");

        return options;
    }

    public void init(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;

        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        //Driver SetUp
        //driver = new ChromeDriver(setChromeOptions());
        driver = new ChromeDriver();
    }
    public boolean isStringInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // 들어오는 os 타입에 따른 크롤링 수행
    public Map<String, Object> crawl(String os, String curReviewDate) {
        ArrayList<CrawlingReview> crawlingList = new ArrayList<>();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        List<WebElement> reviews = null;
        String reviewerAndReviewDateTag;
        String reviewContentTag;
        String reviewStarTag;
        String etcInfoTag;

        try {
            switch (os) {
                case "android_no_login" :
                    driver.get(ANDROID_NO_LOGIN_URL);

                    System.out.println("SOL Android review page load ...");
                    Thread.sleep(WAIT_TIME);
                    System.out.println("SOL Android review page load success");

                    System.out.println("click select tab ...");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_SELECT_TAB)));
                    driver.findElement(By.xpath(ANDROID_SELECT_TAB)).click();
                    Thread.sleep(15000);
                    System.out.println("click select tab success");

                    System.out.println("recently review load ...");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_RECENTLY)));
                    driver.findElement(By.xpath(ANDROID_RECENTLY)).click();
                    Thread.sleep(15000);
                    System.out.println("recently review load success");

                    System.out.println("crawling start");
                    reviews = driver.findElements(By.xpath(ANDROID_NO_LOGIN_REVIEW_LIST_TAG));
                    Thread.sleep(WAIT_TIME);
                    System.out.println("crawling success");

                    if (reviews != null && !reviews.isEmpty()) {

                        for(int i=0; i<reviews.size();i ++) {
                            String tempReviewDate = reviews.get(i).findElement(By.xpath("//body/div[1]/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/div/div[" + (i+1) + "]/div/div[2]/div[1]/div[1]/div/span[2]")).getText();
                            //System.out.println(tempReviewDate);

                            String reviewDate = "";
                            if(isStringInt(tempReviewDate.substring(0,4))) {
                                reviewDate = tempReviewDate.split("년 ")[0];
                                String month = tempReviewDate.split("년 ")[1].split("월 ")[0];
                                String day = tempReviewDate.split("년 ")[1].split("월 ")[1].replace("일","");

                                if(month.length() == 1) {
                                    reviewDate += "0";
                                    reviewDate += month;
                                } else {
                                    reviewDate += month;
                                }
                                if(day.length() == 1) {
                                    reviewDate += "0";
                                    reviewDate += day;
                                } else {
                                    reviewDate += day;
                                }
                            } else {
                                reviewDate += tempReviewDate.split(", ")[1];

                                String monthAndDay = tempReviewDate.split(", ")[0];

                                if(monthAndDay.split(" ")[0].equals("March")) {
                                    reviewDate += "03";
                                    reviewDate += monthAndDay.split(" ")[1];
                                } else if(monthAndDay.split(" ")[0].equals("April")) {
                                    reviewDate += "04";
                                    reviewDate += monthAndDay.split(" ")[1];
                                } else if (monthAndDay.split(" ")[0].equals("May")) {
                                    reviewDate += "05";
                                    reviewDate += monthAndDay.split( " ")[1];
                                }
                            }

                            if (reviewDate.equals(curReviewDate)) {
                                String reviewContent = reviews.get(i).findElement(By.xpath("//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/div/div[" + (i+1) + "]/div/div[2]/div[2]/span[1]")).getText();
                                String tempReviewStar = reviews.get(i).findElement(By.xpath("//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/div/div[" + (i+1) + "]/div/div[2]/div[1]/div[1]/div/span[1]/div/div")).getAttribute("aria-label");
                                String reviewer = reviews.get(i).findElement(By.xpath("//div/div[4]/c-wiz/div/div[2]/div/div[1]/div/div/div[1]/div[2]/div/div[" + (i+1) + "]/div/div[2]/div[1]/div[1]/span")).getText();
                                String reviewStar = String.valueOf(tempReviewStar.replace("별표 5개 만점에 ","").charAt(0));

                                CrawlingReview crawlingReview = new CrawlingReview(
                                        reviewer,
                                        reviewDate,
                                        reviewContent,
                                        reviewStar,
                                        "-",
                                        "-",
                                        "-");



                                crawlingList.add(crawlingReview);
                            }

                        }
                    } else {
                        System.out.println("crawling fail");
                    }

                    break;

                case "android" :
                    driver.get(ANDROID_FAKE_URL);

                    System.out.println("fake page load ...");
                    Thread.sleep(WAIT_TIME);
                    System.out.println("fake page load success");

                    System.out.println("input email ...");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
                    driver.findElement(By.name("identifier")).sendKeys(userId);;
                    Thread.sleep(WAIT_TIME);
                    System.out.println("input email success");

                    System.out.println("click next btn ...");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_LOGIN_NEXT_BTN_TAG)));
                    driver.findElement(By.xpath(ANDROID_LOGIN_NEXT_BTN_TAG)).click();
                    Thread.sleep(15000);
                    System.out.println("click next btn success");

                    System.out.println("input password ...");
                    new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                    driver.findElement(By.name("password")).sendKeys(userPw);
                    Thread.sleep(WAIT_TIME);
                    System.out.println("input password success");

                    //*[@id="submit"]
                    System.out.println("fake login ...");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_LOGIN_OK_BTN_TAG)));
                    driver.findElement(By.xpath(ANDROID_LOGIN_OK_BTN_TAG)).click();
                    System.out.println("fake login success");

                    System.out.println("get base page ...");
                    driver.get(ANDROID_BASE_URL);
                    System.out.println("get base page success");
                    System.out.println("curUrl : " + driver.getCurrentUrl());
                    System.out.println(driver.getTitle());

                    List<WebElement> list = driver.findElements(By.name("identifier"));
                    if (list != null && !list.isEmpty()) {
                        System.out.println("input email ...");
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("identifier")));
                        driver.findElement(By.name("identifier")).sendKeys(userId);;
                        System.out.println("input email success");
                        Thread.sleep(WAIT_TIME);

                        System.out.println("click next btn ...");
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_LOGIN_NEXT_BTN_TAG)));
                        driver.findElement(By.xpath(ANDROID_LOGIN_NEXT_BTN_TAG)).click();
                        System.out.println("click next btn success");
                        Thread.sleep(15000);

                        System.out.println("input password ...");
                        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
                        driver.findElement(By.name("password")).sendKeys(userPw);
                        System.out.println("input password success");
                        Thread.sleep(WAIT_TIME);

                        System.out.println("real login ...");
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(ANDROID_LOGIN_OK_BTN_TAG)));
                        driver.findElement(By.xpath(ANDROID_LOGIN_OK_BTN_TAG)).click();
                        System.out.println("real login success");
                        System.out.println(driver.getTitle());
                        Thread.sleep(25000);
                    }

                    System.out.println("review page load");
                    Thread.sleep(10000);
                    System.out.println("review page end");

                    System.out.println("start crawling ...");
                    /** 리뷰 크롤링 시작 **/
                    reviews = driver.findElements(By.tagName("article"));
                    if (reviews != null && !reviews.isEmpty()) {
                        System.out.println("crawling success");
                        Thread.sleep(WAIT_TIME);
                        /** 리뷰 크롤링 끝 **/

                        // 데이터 파싱을 위한 태그 정보
                        reviewerAndReviewDateTag = "body > div:nth-child(5) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > div > div > div:nth-child(1) > div > div > div > div > section > div:nth-child(4) > div.stickyContainerElement > div:nth-child(3) > section > article> div> div > h2 > span";
                        reviewContentTag = "body > div:nth-child(5) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > div > div > div:nth-child(1) > div > div > div > div > section > div:nth-child(4) > div.stickyContainerElement > div:nth-child(3) > section > article > div > div > div > pre > span";
                        reviewStarTag = "body > div:nth-child(5) > div > div:nth-child(2) > div > div:nth-child(2) > div > div > div > div > div > div:nth-child(1) > div > div > div > div > section > div:nth-child(4) > div.stickyContainerElement > div:nth-child(3) > section > article > div > div > p > div";
                        etcInfoTag = "body > div:nth-child(5) > div > div:nth-child(2) > div > div:nth-child(2) > div > div> div > div > div > div:nth-child(1) > div > div > div > div > section > div:nth-child(4) > div.stickyContainerElement > div:nth-child(3) > section > article > div > div:nth-child(1) > div > div > dl > dd";

                        JSONObject jsonObject = new JSONObject();
                        JSONArray crawlingArr = new JSONArray();

                        //데이터 파싱 작업을 위한 반복문
                        for(int i = 0; i< reviews.size(); i++) {
                            String reviewerAndReviewDate = reviews.get(i).findElement(By.cssSelector(reviewerAndReviewDateTag)).getText();
                            String[] reviewerAndReviewDateArr = reviewerAndReviewDate.split("님이 ");
                            String reviewer = reviewerAndReviewDateArr[0];
                            String[] reviewDateArr = reviewerAndReviewDateArr[1].split(". ");
                            String reviewDate = reviewDateArr[0];
                            if(reviewDateArr[1].length() == 1) {
                                reviewDate += "0";
                                reviewDate += reviewDateArr[1];
                            } else {
                                reviewDate += reviewDateArr[1];
                            }

                            if(reviewDateArr[2].length() == 1) {
                                reviewDate += "0";
                                reviewDate += reviewDateArr[2];
                            } else {
                                reviewDate += reviewDateArr[2];
                            }

                            if (reviewDate.equals(curReviewDate)) {
                                String reviewContent = reviews.get(i).findElement(By.cssSelector(reviewContentTag)).getText();
                                String tempReviewStar = reviews.get(i).findElement(By.cssSelector(reviewStarTag)).getAttribute("title");
                                String reviewStar = tempReviewStar.replace("별표 ","").split("/")[0];
                                List<WebElement> etcInfos = reviews.get(i).findElements(By.cssSelector(etcInfoTag));

                                CrawlingReview crawlingReview = new CrawlingReview(
                                        reviewer,
                                        reviewDate,
                                        reviewContent,
                                        reviewStar,
                                        etcInfos.get(0).getText(),
                                        etcInfos.get(1).getText(),
                                        etcInfos.get(2).getText());

                                JSONObject crawlingReviewJson = new JSONObject();
                                Calendar scal = Calendar.getInstance();
                                String reviewId = new SimpleDateFormat("HHmmss").format(scal.getTime());
                                crawlingReviewJson.put("review_id",reviewId+i);
                                crawlingReviewJson.put("reviewer", reviewer);
                                crawlingReviewJson.put("reviewDate", reviewDate);
                                crawlingReviewJson.put("reviewContent", reviewContent);
                                crawlingReviewJson.put("reviewStar", reviewStar);
                                crawlingReviewJson.put("deviceInfo", etcInfos.get(0).getText());
                                crawlingReviewJson.put("appVersion",  etcInfos.get(1).getText());
                                crawlingReviewJson.put("osVersion", etcInfos.get(2).getText());

                                crawlingList.add(crawlingReview);

                                crawlingArr.add(crawlingReviewJson);
                            }

                            jsonObject.put("crawlingList", crawlingArr);

                        }

                        String jsonInfo = jsonObject.toJSONString();
                        File crawlingFile = new File("D:\\home\\srasdomain\\srasApp\\json\\"+ curReviewDate+"A.json");

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(crawlingFile.getPath()), "EUC-KR"));
                        writer.write(jsonInfo);
                        writer.close();
                        System.out.println(jsonInfo);

                    } else {
                        System.out.println("crawling fail");
                        System.out.println(driver.getPageSource());
                    }

                    break;
                case "ios" :
                    driver.get(IOS_BASE_URL);
                    System.out.println("get ios page load");
                    Thread.sleep(WAIT_TIME);
                    System.out.println("get ios page load success");
                    System.out.println(driver.getCurrentUrl());

                    System.out.println("switch to frame");
                    driver.switchTo().frame(IOS_REVIEW_FRAME_TAG);
                    Thread.sleep(15000);
                    System.out.println("switch to success");
                    System.out.println(driver.getCurrentUrl());

                    /** 로그인 시작 **/
                    System.out.println("input id");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='account_name_text_field']")));
                    driver.findElement(By.xpath("//*[@id='account_name_text_field']")).sendKeys(userId);
                    Thread.sleep(WAIT_TIME);
                    System.out.println("input id success");
                    System.out.println(driver.getCurrentUrl());

                    System.out.println("click login next btn");
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(IOS_LOGIN_NEXT_BTN_TAG)));
                    driver.findElement(By.xpath(IOS_LOGIN_NEXT_BTN_TAG)).click();
                    Thread.sleep(WAIT_TIME);
                    System.out.println("click login next btn success");
                    System.out.println(driver.getCurrentUrl());

                    System.out.println("input password");
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password_text_field']")));
                    driver.findElement(By.xpath("//*[@id='password_text_field']")).sendKeys(userPw);
                    Thread.sleep(WAIT_TIME);
                    System.out.println("input password success");
                    System.out.println(driver.getCurrentUrl());

                    System.out.println("login try");
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(IOS_LOGIN_OK_BTN_TAG)));
                    driver.findElement(By.cssSelector(IOS_LOGIN_OK_BTN_TAG)).click();
                    Thread.sleep(WAIT_TIME);
                    System.out.println("login success");
                    System.out.println(driver.getCurrentUrl());
                    /** 로그인 끝**/

                    Thread.sleep(15000);

                    /** 리뷰 크롤링 시작 **/
                    System.out.println("crawling start");
                    wait = new WebDriverWait(driver, 100);
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(IOS_REVIEW_LIST_TAG)));
                    reviews = driver.findElements(By.xpath(IOS_REVIEW_LIST_TAG));
                    Thread.sleep(25000);
                    System.out.println(driver.getCurrentUrl());

                    if (reviews != null && !reviews.isEmpty()) {
                        System.out.println("crawling success");
                        System.out.println(driver.getCurrentUrl());
                        /** 리뷰 크롤링 끝 **/

                        // 데이터 파싱을 위한 태그 정보
                        reviewerAndReviewDateTag = "//*[@id='infinite-scroll']/div/div[2]/span";
                        reviewContentTag = "#infinite-scroll > div > div.review-body.break-long-lines.ng-isolate-scope";
                        reviewStarTag = "#infinite-scroll > div > div.review-top > h3 > div";
                        etcInfoTag = "#infinite-scroll > div > div.review-footer > ul > li:nth-child(1)";

                        JSONObject jsonObject = new JSONObject();
                        JSONArray crawlingArr = new JSONArray();

                        //데이터 파싱 작업을 위한 반복문
                        for(int i = 0; i< reviews.size(); i++) {
                            String reviewerAndReviewDate = reviews.get(i).findElement(By.xpath("//*[@id='infinite-scroll']/div["+ (i+1) +"]/div[2]/span")).getText();
                            //System.out.println(reviewerAndReviewDate);
                            String reviewer = reviewerAndReviewDate.substring(9).split(" - ")[0];
                            String tempReviewDate = reviewerAndReviewDate.substring(9).split(" - ")[1];
                            String reviewDate = tempReviewDate.split("년 ")[0];
                            String month = tempReviewDate.split("년 ")[1].split("월 ")[0];
                            String day = tempReviewDate.split("년 ")[1].split("월 ")[1].replace("일","");

                            if(month.length() == 1) {
                                reviewDate += "0";
                                reviewDate += month;
                            } else {
                                reviewDate += month;
                            }
                            if(day.length() == 1) {
                                reviewDate += "0";
                                reviewDate += day;
                            } else {
                                reviewDate += day;
                            }

                            if (reviewDate.equals(curReviewDate)) {
                                String reviewContent = reviews.get(i).findElement(By.xpath("//*[@id='infinite-scroll']/div["+(i+1)+"]/div[3]")).getText();
                                String reviewStar = reviews.get(i).findElement(By.xpath("//*[@id='infinite-scroll']/div["+(i+1)+"]/div[1]/h3/div")).getText();
                                String appVersion = reviews.get(i).findElement(By.cssSelector("#infinite-scroll > div:nth-child(" + (i+1) +") > div.review-footer > ul > li:nth-child(1)")).getText();

                                CrawlingReview crawlingReview = new CrawlingReview(
                                        reviewer,
                                        reviewDate,
                                        reviewContent,
                                        reviewStar,
                                        "아이폰",
                                        appVersion,
                                        "-");

                                crawlingList.add(crawlingReview);

                                JSONObject crawlingReviewJson = new JSONObject();
                                Calendar scal = Calendar.getInstance();
                                String reviewId = new SimpleDateFormat("HHmmss").format(scal.getTime());
                                crawlingReviewJson.put("review_id",reviewId+i);
                                crawlingReviewJson.put("reviewer", reviewer);
                                crawlingReviewJson.put("reviewDate", reviewDate);
                                crawlingReviewJson.put("reviewContent", reviewContent);
                                crawlingReviewJson.put("reviewStar", reviewStar);
                                crawlingReviewJson.put("deviceInfo", "아이폰");
                                crawlingReviewJson.put("appVersion",  appVersion);
                                crawlingReviewJson.put("osVersion", "-");

                                crawlingArr.add(crawlingReviewJson);

                            }

                            jsonObject.put("crawlingList", crawlingArr);

                        }
                        String jsonInfo = jsonObject.toJSONString();

                        //String fileName ="C:\\Users\\admin\\Documents\\dev\\sras\\sras\\SRAS_SRC\\sras\\json\\" + curReviewDate+"I.json";
                        File crawlingFile = new File("D:\\home\\srasdomain\\srasApp\\json\\"+ curReviewDate+"I.json");

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(crawlingFile.getPath()), "EUC-KR"));
                        writer.write(jsonInfo);
                        writer.close();
                        System.out.println(jsonInfo);

                    } else {
                        System.out.println("crawling fail");
                        System.out.println(driver.getPageSource());
                    }

                    break;
            }

            Map<String, Object> crawlingMap = new HashMap<>();
            crawlingMap.put("crawlingList", crawlingList);

            return crawlingMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.manage().deleteAllCookies();
            driver.close();
        }
        return null;
    }
}