package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.dao.CommentDao;
import com.shinhan.sras.model.Comment;
import com.shinhan.sras.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
	CommentDao commentDao;

    @Override
    public Map<String, Object> commentServiceTest() {
        Map<String , Object> res = commentDao.commentDaoTest();
        return res;
    }

    @Override
    public Map<String, Object> getCommentList(String reviewDate, String reviewId) throws Exception {
        return commentDao.selectCommentList(reviewDate,reviewId);
    }

    @Override
    public Map<String, Object> registerComment(String reviewDate, String reviewId, Map<String, Object> comment) throws Exception{
        //Comment tempComment = new Comment(reviewId, comment.get("userId").toString(), comment.get("comment").toString(), comment.get("commentDate").toString());
        return commentDao.insertComment(reviewDate, reviewId, comment.get("userId").toString(),  comment.get("comment").toString());
    }

    @Override
    public Map<String, Object> modifyComment(String reviewDate, String reviewId, Map<String, Object> comment) throws Exception {
        Comment modifyComment = new Comment(comment.get("commentId").toString(), reviewId, comment.get("userId").toString(), comment.get("comment").toString(), comment.get("commentDate").toString());
        return commentDao.updateComment(reviewDate, reviewId, comment.get("commentId").toString(), comment.get("userId").toString(),comment.get("comment").toString());
    }

}
