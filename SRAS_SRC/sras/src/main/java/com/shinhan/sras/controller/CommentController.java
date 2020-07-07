package com.shinhan.sras.controller;

import com.shinhan.sras.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
	CommentService commentService;

    @GetMapping("/test")
    public Map<String, Object> commentTest() throws Exception {
        return commentService.commentServiceTest();
    }

    @GetMapping("/get/list/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> getCommentList(@PathVariable String reviewDate ,@PathVariable String reviewId) throws Exception {
        return commentService.getCommentList(reviewDate, reviewId);
    }

    @PostMapping("/register/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> registerComment(@PathVariable String reviewDate, @PathVariable String reviewId, @RequestBody Map<String, Object> comment) throws Exception {
        return commentService.registerComment(reviewDate, reviewId, comment);
    }

    @PutMapping("/modify/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> modifyComment(@PathVariable String reviewDate, @PathVariable String reviewId, @RequestBody Map<String, Object> comment) throws Exception {
        return commentService.modifyComment(reviewDate, reviewId, comment);
    }
}
