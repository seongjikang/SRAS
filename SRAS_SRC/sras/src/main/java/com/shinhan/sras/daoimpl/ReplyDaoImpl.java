package com.shinhan.sras.daoimpl;

import com.shinhan.sras.dao.ReplyDao;
import com.shinhan.sras.mapper.ReviewMapper;
import com.shinhan.sras.model.Reply;
import com.shinhan.sras.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ReplyDaoImpl implements ReplyDao {

    public static ArrayList<Reply> replies;

    @Autowired
    private ReviewMapper reviewMapper;

    static {
        replies = new ArrayList<>();

        //review data
        ArrayList<Tag> tagList1 =new ArrayList<>();
        tagList1.add(new Tag("칭찬글"));
        tagList1.add(new Tag("안드로이드"));
        Reply reply1 = new Reply("00000001", "감사합니다.", "18100030", "20200415", "", "incomplete", tagList1, "");

        replies.add(reply1);
    }

    @Override
    public Map<String, Object> selectAllReply() {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", replies);
        return res;
    }

    @Override
    public Map<String, Object> selectReply(String managerId, String reviewDate, String reviewId) {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        Reply tempReply = replies
                .stream()
                .filter(review -> review.getReplyManagerId().equals(managerId) && review.getReviewId().equals(reviewId))
                .findAny()
                .orElse(new Reply("-1", "", "-1", "", "", "", null, ""));

        res.put("data", tempReply);
        return res;
    }

    @Override
    public Map<String, Object> insertReply(Reply reply) {
        replies.add(reply);
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");

        return  res;
    }

    @Override
    public Map<String, Object> updateReply(String managerId, String reviewDate, String reviewId, String replyContent, String tagList) throws Exception {
        reviewMapper.updateReply(managerId, reviewDate, reviewId, replyContent, tagList);

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");
        return  res;

    }

    @Override
    public Map<String, Object> updateApprove(String reviewDate, String reviewId, String approveChk, String reason) throws Exception {

        Date today = new Date();

        SimpleDateFormat sfm = new SimpleDateFormat("yyyyMMdd");

        String approveDate = sfm.format(today);

        reviewMapper.updateApprove(reviewDate, reviewId, approveChk, reason, approveDate);

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");
        return  res;
    }
}
