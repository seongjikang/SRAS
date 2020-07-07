package com.shinhan.srasCrawler.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface CrawlingDao {

    Map<String, Object> selectPassword(String os);
}
