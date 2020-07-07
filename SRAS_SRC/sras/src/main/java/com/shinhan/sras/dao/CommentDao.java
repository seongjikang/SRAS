package com.shinhan.sras.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CommentDao {
    Map<String, Object> commentDaoTest();
    Map<String, Object> selectCommentList(String reviewDate, String reviewId) throws Exception;
    Map<String, Object> insertComment(String reviewDate, String reviewId, String userId, String comment) throws Exception;
    Map<String, Object> updateComment(String reviewDate, String reviewId, String commentId, String userId, String comment) throws Exception;
}
