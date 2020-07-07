package com.shinhan.sras.controller;

import com.shinhan.sras.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
	ReviewService reviewService;

    @GetMapping("/test")
    public Map<String, Object> userTest() {
        return reviewService.reviewServiceTest();
    }

    @GetMapping("/get/list/osType/{osType}/state/{state}")
    public Map<String,Object> getReviewList(@PathVariable String osType, @PathVariable String state, @RequestParam String offset, @RequestParam String itemsPerPage,  @RequestParam String searchText, @RequestParam String tagList, @RequestParam String startDate, @RequestParam String endDate) throws Exception {
        return reviewService.getReviewList(osType, state, Integer.parseInt(offset), Integer.parseInt(itemsPerPage), searchText, tagList, startDate, endDate);
    }

    @GetMapping("/detail/info/userId/{userId}/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> getDetailReview(@PathVariable String reviewDate, @PathVariable String reviewId) throws Exception {
        return reviewService.getDetailReview(reviewDate, reviewId);
    }

    @GetMapping("/get/recommendReview/reviewDate/{reviewDate}/reviewId/{reviewId}/recommendReviewNo/{recommendReviewNo}")
    public Map<String,Object> getRecommendReview(@PathVariable String reviewDate, @PathVariable String reviewId, @PathVariable String recommendReviewNo) throws Exception{
        return reviewService.getRecommendReview(reviewDate, reviewId, recommendReviewNo);
    }
}
