package com.shinhan.sras.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserDao {
    Map<String, Object> userDaoTest ();
    Map<String, Object> confirmUserId(String userId);
    Map<String, Object> selectUserName(String userId);
    boolean login(String id, String password);
}
