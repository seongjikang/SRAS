package com.shinhan.srasCrawler.daoImpl;

import com.shinhan.srasCrawler.dao.CrawlingDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CrawlingDaoImpl implements CrawlingDao {
    public static Map<String, Object> accountInfo;

    static {
        accountInfo = new HashMap<>();
        
        accountInfo.put("userIda", "shdigitaltech@gmail.com");
        accountInfo.put("userIdi", "shbsmartmgr@gmail.com");

        accountInfo.put("android", "Shinhan012!!");
        accountInfo.put("ios", "Shinhan012!!");
    }

    @Override
    public Map<String, Object> selectPassword(String os) {
        Map<String, Object> res = new HashMap<>();

        // Todo : 크롤링을 위한 계정 정보 db 작업 필요

        if(os.equals("android")) {
            res.put("userId", accountInfo.get("userIda").toString());
            res.put("userPw", accountInfo.get("android").toString());
        } else {
            res.put("userId", accountInfo.get("userIdi").toString());
            res.put("userPw", accountInfo.get("ios").toString());
        }

        return res;
    }
}
