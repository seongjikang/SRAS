package com.shinhan.sras.controller;

        import com.shinhan.sras.dao.SrasDao;
        import com.shinhan.sras.model.Sras;
        import com.shinhan.sras.service.MainService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.CrossOrigin;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/")
public class HomeController {

    @Autowired
    MainService mainService;

    @GetMapping("")
    public Map<String, Object> home() {

        //Test Code.
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("key","value");

        return  map;
    }

    @GetMapping("test/")
    public List<Sras> test() throws  Exception {
        return mainService.getUserList();
    }

}
