package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.dao.ReviewDao;
import com.shinhan.sras.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewDao reviewDao;

    @Override
    public Map<String, Object> reviewServiceTest() {
        Map<String , Object> res = reviewDao.reviewDaoTest();
        return res;
    }

    @Override
    public Map<String, Object> getReviewList(String osType, String state, Integer offset, Integer itemsPerPage, String searchText, String tagList, String startDate, String endDate) throws Exception {
        //TODO : 페이징 처리 , #24
        if(tagList == null ) {
            return reviewDao.selectReviewList(osType, state, searchText, "", startDate, endDate);
        } else {
            return reviewDao.selectReviewList(osType, state, searchText, tagList, startDate, endDate);
        }
    }

    @Override
    public Map<String, Object> getDetailReview(String reviewDate, String reviewId) throws Exception {
        return reviewDao.selectDetailReview(reviewDate, reviewId);
    }

    @Override
    public Map<String, Object> getRecommendReview(String reviewDate, String reviewId, String recommendReviewNo) throws Exception {
        return reviewDao.selectRecommendReview(reviewDate, reviewId, recommendReviewNo);
    }
}
