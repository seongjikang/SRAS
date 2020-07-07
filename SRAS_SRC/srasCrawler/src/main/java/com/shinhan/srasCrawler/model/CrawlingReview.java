package com.shinhan.srasCrawler.model;

public class CrawlingReview {
    private String reviewer;
    private String reviewDate;
    private String reviewContent;
    private String reviewStar;
    private String deviceInfo;
    private String appVersion;
    private String osVersion;

    public CrawlingReview() {
    }

    public CrawlingReview(String reviewer, String reviewDate, String reviewContent, String reviewStar, String deviceInfo, String appVersion, String osVersion) {
        this.reviewer = reviewer;
        this.reviewDate = reviewDate;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.deviceInfo = deviceInfo;
        this.appVersion = appVersion;
        this.osVersion = osVersion;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(String reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }
}
