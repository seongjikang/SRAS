package com.shinhan.sras.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ReviewDao {
    Map<String, Object> reviewDaoTest();
    Map<String, Object> selectReviewList(String osType, String state, String searchText, String tagList, String startDate, String endDate) throws Exception;
    Map<String, Object> selectDetailReview(String reviewDate, String reviewId) throws Exception;
    Map<String, Object> selectRecommendReview(String reviewDate, String reviewId, String recommendReviewNo) throws Exception;

}
