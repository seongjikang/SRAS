package com.shinhan.sras.controller;

import com.shinhan.sras.model.Tag;
import com.shinhan.sras.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/get/all/tag")
    public Map<String, Object> getAllTag() throws Exception {
        return tagService.getAllTag();
    }

    @PostMapping("/register")
    public Map<String, Object> registerTag(@RequestBody Tag tag) throws Exception {
        return tagService.registerTag(tag);
    }

    @DeleteMapping("/delete/tagContent/{tagContent}")
    public Map<String, Object> delet2eTag(@PathVariable String tagContent) throws Exception {
        return tagService.deleteTag(tagContent);
    }
}
