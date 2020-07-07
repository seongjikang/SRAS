package com.shinhan.sras.daoimpl;

import com.shinhan.sras.dao.UserDao;
import com.shinhan.sras.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {

    public static ArrayList<User> users;

    static {
        users = new ArrayList<>();

        //user data
        users.add(new User("20200129", "20200129", "이소라", "D", "replier"));
        users.add(new User("18100030", "18100030", "강성지", "D", "replier"));
        users.add(new User("20200188", "20200188", "이지민", "D", "replier"));
        users.add(new User("20200099", "20200099", "김혜준", "D", "replier"));
        users.add(new User("18201369", "18201369", "최리나", "D", "replier"));
        users.add(new User("16101618", "16101618", "박창균", "D", "replier"));
        users.add(new User("18101534", "18101534", "김낙진", "D", "replier"));
        users.add(new User("18100309", "18100309", "문지만", "D", "replier"));
        users.add(new User("20100140", "20100140", "안동원", "D", "replier"));
        users.add(new User("19200196", "19200196", "권은지", "D", "replier"));
        users.add(new User("19201524", "19201524", "나은별", "D", "replier"));



        users.add(new User("06144144", "06144144", "어택우", "D", "admin"));
        users.add(new User("06150020", "06150020", "이원종", "D", "admin"));
        users.add(new User("06174582", "06174582", "유승용", "D", "admin"));
        users.add(new User("20000049", "20000049", "배시형", "D", "admin"));
    }

    @Override
    public Map<String, Object> userDaoTest() {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("userKey1","user1");
        res.put("userKey2","user2");
        res.put("userKey3","user3");

        return res;
    }

    @Override
    public Map<String, Object> confirmUserId(String userId) {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        //user data
        User confirmUser = users
                .stream()
                .filter(user-> user.getUserId().equals(userId))
                .findAny()
                .orElse(new User("-1", "", "", "", ""));

        res.put("data", confirmUser);

        return res;
    }

    @Override
    public Map<String, Object> selectUserName(String userId) {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        //user data
        String tempUser = users
                .stream()
                .filter(user-> user.getUserId().equals(userId))
                .findAny()
                .orElse(new User("-1", "", "", "", ""))
                .getUserName();

        res.put("data", tempUser);

        return res;
    }

    @Override
    public boolean login(String id, String password) {

        boolean resLogin = false;

        for (int i = 0 ; i< users.size(); i++) {
            if (id.equals(users.get(i).getUserId())) {
                if(password.equals(users.get(i).getPassword())) {
                    resLogin = true;
                    break;
                } else {
                    resLogin = false;
                }
            } else {
                resLogin = false;
            }
        }
        return resLogin;
    }
}
