package com.shinhan.sras.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface UserService {
    Map<String, Object> userServiceTest();
    Map<String, Object> confirmUserId(String userId);
    Map<String, Object> login(String userId, String userPassword);
    Map<String, Object> getUserName(String userId);
}
