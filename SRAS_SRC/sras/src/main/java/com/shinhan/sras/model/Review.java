package com.shinhan.sras.model;

import java.util.ArrayList;

public class Review {
    private String reviewId;
    private String reviewContent;
    private String osType;
    private String osVersion;
    private Integer reviewStar;
    private String reviewDate;
    private String deviceInfo;
    private String appVersion;

    public Review() {
    }

    public Review(String reviewId, String reviewContent, String osType, String osVersion, Integer reviewStar, String reviewDate, String deviceInfo, String appVersion) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.osType = osType;
        this.osVersion = osVersion;
        this.reviewStar = reviewStar;
        this.reviewDate = reviewDate;
        this.deviceInfo = deviceInfo;
        this.appVersion = appVersion;

    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(Integer reviewStar) {
        this.reviewStar = reviewStar;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
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


//    public ArrayList<Tag> getTagList() {
//        return tagList;
//    }

//    public void setTagList(ArrayList<Tag> tagList) {
//        this.tagList = tagList;
//    }
}