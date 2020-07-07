package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.dao.UserDao;
import com.shinhan.sras.service.SecurityService;
import com.shinhan.sras.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    SecurityService securityService;

    @Override
    public Map<String, Object> userServiceTest() {
        Map<String, Object> res = userDao.userDaoTest();
        return res;
    }

    @Override
    public Map<String, Object> confirmUserId(String userId) {
        Map<String, Object> res = userDao.confirmUserId(userId);
        return res;
    }

    @Override
    public Map<String, Object> login(String userId, String userPassword) {

        Map<String, Object> res = new HashMap<>();

        boolean resultLogin = userDao.login(userId, userPassword);

        if (resultLogin) {
            res.put("success", true);
            res.put("msg","");

            String token = securityService.createToken(userId, (10 * 1000 * 60));
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("token", token);
            res.put("data", map);
        } else {
            res.put("success", false);
            res.put("msg","Login Failed");
            res.put("data", "");
        }

        return res;
    }

    @Override
    public Map<String, Object> getUserName(String userId) {
        Map<String, Object> res = userDao.selectUserName(userId);
        return res;
    }
}
