package com.shinhan.sras.controller;

import com.shinhan.sras.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://192.168.204.180:25111")
@RequestMapping("/replies")
public class ReplyController {
    @Autowired
	ReplyService replyService;

    @GetMapping("/get/all/reply")
    public Map<String, Object> getAllReply() {
        return replyService.getAllReply();
    }

    @GetMapping("/get/reply/managerId/{managerId}/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> getReply(@PathVariable String managerId, @PathVariable String reviewDate, @PathVariable String reviewId) {
        return replyService.getReply(managerId, reviewDate, reviewId);
    }

    @PostMapping("/register/managerId/{managerId}/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> registerReply(@PathVariable String managerId, @PathVariable String reviewDate, @PathVariable String reviewId, @RequestBody Map<String, Object> review) {
        return replyService.registerReply(managerId, reviewDate, reviewId, review);
    }

    @PutMapping("/modify/managerId/{managerId}/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> modifyReply(@PathVariable String managerId, @PathVariable String reviewDate, @PathVariable String reviewId, @RequestBody Map<String, Object> review) throws Exception {
        return replyService.modifyReply(managerId, reviewDate, reviewId, review);
    }

    @PutMapping("/approve/reviewDate/{reviewDate}/reviewId/{reviewId}")
    public Map<String, Object> approveReply(@PathVariable String reviewDate, @PathVariable String reviewId, @RequestBody Map<String, Object> approve) throws  Exception {
        return replyService.approveReply(reviewDate, reviewId, approve);
    }
}
