package com.shinhan.srasCrawler.controller;

import com.shinhan.srasCrawler.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/crawlers")
public class CrawlingController {
    @Autowired
    CrawlingService crawlingService;

    @GetMapping("/android/reviews/reviewDate/{reviewDate}")
    public Map<String, Object> getAndroidReviews(@PathVariable String reviewDate) {
        return crawlingService.getAndroidReviews(reviewDate);
    }

    @GetMapping("/ios/reviews/reviewDate/{reviewDate}")
    public Map<String, Object> getIosReviews(@PathVariable String reviewDate) {
        return crawlingService.getIosReviews(reviewDate);
    }

}
