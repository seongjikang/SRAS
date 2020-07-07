package com.shinhan.sras.mapper;

import com.shinhan.sras.model.Comment;

import java.util.List;

public interface CommentMapper {
    public List<Comment> selectCommentList(String reviewDate, String reviewId) throws Exception;
    public void insertComment(String reviewDate, String reviewId, String commentId, String userId, String comment, String commentDate) throws  Exception;
    public void updateComment(String reviewDate, String reviewId, String commentId, String userId, String comment, String commentDate) throws Exception;
}
