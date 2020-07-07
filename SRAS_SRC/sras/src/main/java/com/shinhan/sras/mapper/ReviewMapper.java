package com.shinhan.sras.mapper;

import com.shinhan.sras.model.RecommendReview;
import com.shinhan.sras.model.ReviewAndReply;
import com.shinhan.sras.model.Tag;

import java.util.List;

public interface ReviewMapper {
    public List<ReviewAndReply> selectReviewList(String osType, String state, String searchText, String tagList, String startDate, String endDate) throws Exception;
    public ReviewAndReply selectDetailReview(String reviewDate, String reviewId) throws Exception;
    public void updateReply(String managerId, String reviewDate, String reviewId, String replyContent, String tagList) throws Exception;
    public void updateApprove(String reviewDate, String reviewId, String approveChk, String reason, String approveDate) throws Exception;
    public List<Tag> selectDeleteTag(String deleteTag);
    public RecommendReview selectRecommendReview(String reviewDate, String reviewId, String recommendReviewNo);
    public void deleteTag(String beforeTag, String afterTag) throws Exception;
}
