package com.shinhan.sras.model;

public class ReviewAndReply {
    private String reviewId;
    private String reviewContent;
    private String osType;
    private String osVersion;
    private String reviewStar;
    private String reviewDate;
    private String deviceInfo;
    private String maker;
    private String appVersion;
    private String buildNo;
    private String replyContent;
    private String replyManagerId;
    private String replyManagerName;
    private String requestDate;
    private String approveDate;
    private String approveChk;
    private String tags;
    private String reason;

    public ReviewAndReply() {
    }

    public ReviewAndReply(String reviewId, String reviewContent, String osType, String osVersion, String reviewStar, String reviewDate, String deviceInfo, String maker, String appVersion, String buildNo, String replyContent, String replyManagerId, String replyManagerName, String requestDate, String approveDate, String approveChk, String tags, String reason) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.osType = osType;
        this.osVersion = osVersion;
        this.reviewStar = reviewStar;
        this.reviewDate = reviewDate;
        this.deviceInfo = deviceInfo;
        this.maker = maker;
        this.appVersion = appVersion;
        this.buildNo = buildNo;
        this.replyContent = replyContent;
        this.replyManagerId = replyManagerId;
        this.replyManagerName = replyManagerName;
        this.requestDate = requestDate;
        this.approveDate = approveDate;
        this.approveChk = approveChk;
        this.tags = tags;
        this.reason = reason;
    }

    public ReviewAndReply(String reviewId, String reviewContent, String osType, String osVersion, String reviewStar, String reviewDate, String deviceInfo, String appVersion, String replyContent, String replyManagerId, String approveDate, String approveChk, String tags, String reason) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.osType = osType;
        this.osVersion = osVersion;
        this.reviewStar = reviewStar;
        this.reviewDate = reviewDate;
        this.deviceInfo = deviceInfo;
        this.maker = "-";
        this.appVersion = appVersion;
        this.buildNo = "-";
        this.replyContent = replyContent;
        this.replyManagerId = replyManagerId;
        this.replyManagerName = "-";
        this.requestDate = "-";
        this.approveDate = approveDate;
        this.approveChk = approveChk;
        this.tags = tags;
        this.reason = reason;
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

    public String getReviewStar() {
        return reviewStar;
    }

    public void setReviewStar(String reviewStar) {
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

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyManagerId() {
        return replyManagerId;
    }

    public void setReplyManagerId(String replyManagerId) {
        this.replyManagerId = replyManagerId;
    }

    public String getReplyManagerName() {
        return replyManagerName;
    }

    public void setReplyManagerName(String replyManagerName) {
        this.replyManagerName = replyManagerName;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public String getApproveChk() {
        return approveChk;
    }

    public void setApproveChk(String approveChk) {
        this.approveChk = approveChk;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
