package com.shinhan.sras.controller;

import com.shinhan.sras.service.SecurityService;
import com.shinhan.sras.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/test")
    public Map<String, Object> userTest() {
        return userService.userServiceTest();
    }

    @GetMapping("/confirm/authority/userId/{userId}")
    public Map<String, Object> confirmUserId(@PathVariable String userId) {
        return userService.confirmUserId(userId);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody HashMap<String, Object> user) {

        // TODO : 골드윙 api 연동 후 로그인 하기, #17

        return userService.login(user.get("userId").toString(), user.get("password").toString());
    }

    @GetMapping("/get/userName/userId/{userId}")
    public Map<String, Object> getUserName(@PathVariable String userId) { return userService.getUserName(userId); }

}
