package com.shinhan.sras.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CommentService {
    Map<String, Object> commentServiceTest() throws Exception;
    Map<String, Object> getCommentList(String reviewDate, String reviewId) throws Exception;
    Map<String, Object> registerComment(String reviewDate, String reviewId, Map<String, Object> comment) throws Exception;
    Map<String, Object> modifyComment(String reviewDate, String reviewId, Map<String, Object> comment) throws Exception;
}
