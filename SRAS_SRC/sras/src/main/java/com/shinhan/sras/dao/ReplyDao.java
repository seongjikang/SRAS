package com.shinhan.sras.dao;

import com.shinhan.sras.model.Reply;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ReplyDao {
    Map<String, Object> selectAllReply();
    Map<String, Object> selectReply(String managerId, String reviewDate, String reviewId);
    Map<String, Object> insertReply(Reply reply);
    Map<String, Object> updateReply(String managerId, String reviewDate, String reviewId, String replyContent, String tagList) throws Exception;
    Map<String, Object> updateApprove(String reviewDate, String reviewId, String approveChk, String reason) throws Exception;
}
