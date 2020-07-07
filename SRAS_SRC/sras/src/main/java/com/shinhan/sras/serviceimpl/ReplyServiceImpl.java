package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.dao.ReplyDao;
import com.shinhan.sras.model.Reply;
import com.shinhan.sras.model.Tag;
import com.shinhan.sras.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
	ReplyDao replyDao;

    @Override
    public Map<String, Object> getAllReply() {
        return replyDao.selectAllReply();
    }

    @Override
    public Map<String, Object> getReply(String managerId, String reviewDate, String reviewId) {
        return replyDao.selectReply(reviewDate, managerId, reviewId);
    }

    @Override
    public Map<String, Object> registerReply(String managerId, String reviewDate, String reviewId, Map<String, Object> reply) {
        return replyDao.insertReply(new Reply(
                                        reviewId,
                                        reply.get("replyContent").toString(),
                                        managerId,
                                        new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()),
                                        "",
                                        "incomplete",
                                        (ArrayList<Tag>)reply.get("tagList"),
                                        "" ));
    }

    @Override
    public Map<String, Object> modifyReply(String managerId, String reviewDate, String reviewId, Map<String, Object> review)  throws Exception {
        return replyDao.updateReply(managerId, reviewDate, reviewId, review.get("replyContent").toString(), review.get("tagList").toString());
    }

    @Override
    public Map<String, Object> approveReply(String reviewDate, String reviewId, Map<String, Object> approve) throws  Exception{
        return replyDao.updateApprove(reviewDate, reviewId, approve.get("approveChk").toString(), approve.get("reason").toString());
    }
}
