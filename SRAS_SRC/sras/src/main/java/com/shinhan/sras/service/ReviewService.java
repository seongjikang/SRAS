package com.shinhan.sras.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ReviewService {
    Map<String, Object> reviewServiceTest();
    Map<String, Object> getReviewList(String osType, String state, Integer offset, Integer itemsPerPage, String searchText, String tagList, String startDate, String endDate) throws Exception;
    Map<String, Object> getDetailReview(String reviewDate, String reviewId) throws Exception;
    Map<String,Object> getRecommendReview(String reviewDate, String reviewId, String recommendReviewNo) throws Exception;
}
