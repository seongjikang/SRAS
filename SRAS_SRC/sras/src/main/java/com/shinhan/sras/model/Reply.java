package com.shinhan.sras.model;

import java.util.ArrayList;

public class Reply {
    private String reviewId;
    private String replyContent;
    private String replyManagerId;
    private String requestDate;
    private String approveDate;
    private String approveChk;
    private ArrayList<Tag> tags;
    private String reason;

    public Reply() {
    }

    public Reply(String reviewId, String replyContent, String replyManagerId, String requestDate, String approveDate, String approveChk, ArrayList<Tag> tags, String reason) {
        this.reviewId = reviewId;
        this.replyContent = replyContent;
        this.replyManagerId = replyManagerId;
        this.requestDate = requestDate;
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

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
