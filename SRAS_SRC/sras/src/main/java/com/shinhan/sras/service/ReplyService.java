package com.shinhan.sras.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ReplyService {
    Map<String, Object> getAllReply();
    Map<String, Object> getReply(String managerId, String reviewDate, String reviewId);
    Map<String, Object> registerReply(String managerId, String reviewDate, String reviewId, Map<String, Object> review);
    Map<String, Object> modifyReply(String managerId, String reviewDate, String reviewId, Map<String, Object> review) throws Exception;
    Map<String, Object> approveReply(String reviewDate, String reviewId, Map<String, Object> approve) throws Exception;
}
