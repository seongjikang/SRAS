package com.shinhan.srasCrawler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("")
    public Map<String, Object> home() {

        //Test Code.
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("key","value");

        return  map;
    }
}
