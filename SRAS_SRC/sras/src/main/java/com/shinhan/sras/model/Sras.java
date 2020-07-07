package com.shinhan.sras.model;

public class Sras {
    private String reviewDate;
    private String reviewId;
    private String reviewer;
    private String reviewContent;
    private String reviewStar;
    private String deviceInfo;
    private String appVersion;
    private String osKind;
    private String osVersion;
    private String tag;
    private String reviewReply;
    private String etcData1;
    private String etcData2;
    private String etcData3;

    public Sras() {
    }

    public Sras(String reviewDate, String reviewId, String reviewer, String reviewContent, String reviewStar, String deviceInfo, String appVersion, String osKind, String osVersion, String tag, String reviewReply, String etcData1, String etcData2, String etcData3) {
        this.reviewDate = reviewDate;
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.deviceInfo = deviceInfo;
        this.appVersion = appVersion;
        this.osKind = osKind;
        this.osVersion = osVersion;
        this.tag = tag;
        this.reviewReply = reviewReply;
        this.etcData1 = etcData1;
        this.etcData2 = etcData2;
        this.etcData3 = etcData3;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
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

    public String getOsKind() {
        return osKind;
    }

    public void setOsKind(String osKind) {
        this.osKind = osKind;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getReviewReply() {
        return reviewReply;
    }

    public void setReviewReply(String reviewReply) {
        this.reviewReply = reviewReply;
    }

    public String getEtcData1() {
        return etcData1;
    }

    public void setEtcData1(String etcData1) {
        this.etcData1 = etcData1;
    }

    public String getEtcData2() {
        return etcData2;
    }

    public void setEtcData2(String etcData2) {
        this.etcData2 = etcData2;
    }

    public String getEtcData3() {
        return etcData3;
    }

    public void setEtcData3(String etcData3) {
        this.etcData3 = etcData3;
    }
}
