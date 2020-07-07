package com.shinhan.sras.daoimpl;

import com.shinhan.sras.dao.ReviewDao;
import com.shinhan.sras.mapper.ReviewMapper;
import com.shinhan.sras.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Map<String, Object> reviewDaoTest() {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("reviewKey1","review1");
        res.put("reviewKey2","review2");
        res.put("reviewKey3","review3");

        return res;
    }

    @Override
    public Map<String, Object> selectReviewList(String osType, String state, String searchText, String tagList, String startDate, String endDate) throws Exception {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        String[] inputTags = tagList.split(",");

        List<ReviewAndReply> reviewAndReplies = reviewMapper.selectReviewList(osType, state, "%"+searchText+"%", tagList, startDate, endDate);

        List<ReviewAndReply> androidReviewAndReplies = new ArrayList<>();
        List<ReviewAndReply> iosReviewAndReplies = new ArrayList<>();

        Set<ReviewAndReply> reviewAndRepliesSet = new LinkedHashSet<>();

        for(int i=0; i<reviewAndReplies.size();i++) {
            if(reviewAndReplies.get(i).getOsType().equals("A")) {
               androidReviewAndReplies.add(reviewAndReplies.get(i));
            } else if(reviewAndReplies.get(i).getOsType().equals("I")) {
                iosReviewAndReplies.add(reviewAndReplies.get(i));
            }
        }

        if(osType.equals("A")) {
            if(inputTags.length == 1) {
                if(inputTags[0].equals("")) {
                    res.put("data", androidReviewAndReplies);
                } else {
                    for(ReviewAndReply reviewAndReply : androidReviewAndReplies) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[0])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }
                    res.put("data", reviewAndRepliesSet);
                }
            } else if (inputTags.length > 1) {
                for(ReviewAndReply reviewAndReply : androidReviewAndReplies) {
                    for(int i=0;i <inputTags.length; i++) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[i])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }

                }
                res.put("data", reviewAndRepliesSet);
            }

        } else if(osType.equals("I")) {
            if(inputTags.length == 1) {
                if(inputTags[0].equals("")) {
                    res.put("data", iosReviewAndReplies);
                } else {
                    for(ReviewAndReply reviewAndReply : iosReviewAndReplies) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[0])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }
                    res.put("data", reviewAndRepliesSet);
                }
            } else if (inputTags.length > 1) {
                for(ReviewAndReply reviewAndReply : iosReviewAndReplies) {
                    for(int i=0;i <inputTags.length; i++) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[i])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }

                }
                res.put("data", reviewAndRepliesSet);
            }
        } else {
            if(inputTags.length == 1) {
                if(inputTags[0].equals("")) {
                    res.put("data", reviewAndReplies);
                } else {
                    for(ReviewAndReply reviewAndReply : reviewAndReplies) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[0])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }
                    res.put("data", reviewAndRepliesSet);
                }
            } else if (inputTags.length > 1) {
                for(ReviewAndReply reviewAndReply : reviewAndReplies) {
                    for(int i=0;i <inputTags.length; i++) {
                        if(reviewAndReply.getTags() != null) {
                            if (reviewAndReply.getTags().contains(inputTags[0])) {
                                reviewAndRepliesSet.add(reviewAndReply);
                            }
                        }
                    }

                }
                res.put("data", reviewAndRepliesSet);
            }
        }

        return res;
    }

    @Override
    public Map<String, Object> selectDetailReview(String reviewDate, String reviewId) throws  Exception{
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        ReviewAndReply detailReview = reviewMapper.selectDetailReview(reviewDate, reviewId);

        res.put("data", detailReview);
        return res;
    }

    @Override
    public Map<String, Object> selectRecommendReview(String reviewDate, String reviewId, String recommendReviewNo) throws Exception {
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        RecommendReview recommendReview = reviewMapper.selectRecommendReview(reviewDate,reviewId,recommendReviewNo);

        res.put("data", recommendReview);
        return res;
    }
}
