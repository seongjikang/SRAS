package com.shinhan.srasRunner.SrasRunner.runner;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class SrasCrawlingRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("start sol review crawling");
        OkHttpClient client;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(600, TimeUnit.SECONDS);
        builder.readTimeout(600, TimeUnit.SECONDS);
        builder.writeTimeout(600, TimeUnit.SECONDS);
        client = builder.build();

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();

        cal.add(cal.DATE, -1);
        String yesterday = date.format(cal.getTime());

        File lastDateFile = new File("D:\\home\\srasdomain\\srasApp\\data\\lastDate.txt");
        String temp;
        String lastDate="";
        BufferedReader br = new BufferedReader(new FileReader(lastDateFile));
        while ((temp = br.readLine()) != null) {
            lastDate = temp;
        }

        ArrayList<String> dates = new ArrayList<String>();
        Date currentDate = date.parse(lastDate);

        while (currentDate.compareTo(date.parse(yesterday)) <= 0) {
            dates.add(date.format(currentDate));
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            currentDate = c.getTime();
        }

        ArrayList<String> noInputDates = new ArrayList<>();

        for (String noInputDate : dates) {
            System.out.println(noInputDate);
            noInputDates.add(noInputDate);
        }

        for (int i= 1; i<noInputDates.size(); i++) {
            Request iosRequest = new Request.Builder()
                    .url("http://localhost:45111/crawlers/ios/reviews/reviewDate/"+noInputDates.get(i))
                    .method("GET", null)
                    .build();
            Response iosResponse = client.newCall(iosRequest).execute();

            Request androidRequest = new Request.Builder()
                    .url("http://localhost:45111/crawlers/android/reviews/reviewDate/"+noInputDates.get(i))
                    .method("GET", null)
                    .build();
            Response androidResponse = client.newCall(androidRequest).execute();
        }
        br.close();
    }
}
