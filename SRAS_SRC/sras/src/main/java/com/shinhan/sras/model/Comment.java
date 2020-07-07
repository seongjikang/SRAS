package com.shinhan.sras.model;

public class Comment {
    private String commentId;
    private String reviewId;
    private String userId;
    private String comment;
    private String commentDate;

    public Comment() {
    }

    public Comment(String commentId, String reviewId, String userId, String comment, String commentDate) {
        this.commentId = commentId;
        this.reviewId = reviewId;
        this.userId = userId;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }
}
