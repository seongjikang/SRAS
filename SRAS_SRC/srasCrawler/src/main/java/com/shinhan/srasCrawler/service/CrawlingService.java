package com.shinhan.srasCrawler.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface CrawlingService {
    Map<String, Object> getAndroidReviews(String reviewDate);
    Map<String, Object> getIosReviews(String reviewDate);
}
