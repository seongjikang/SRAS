package com.shinhan.sras.daoimpl;

import com.shinhan.sras.dao.CommentDao;
import com.shinhan.sras.mapper.CommentMapper;
import com.shinhan.sras.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Map<String, Object> commentDaoTest() {
        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("commentKey1","comment1");
        res.put("commentKey2","comment2");
        res.put("commentKey3","comment3");

        return res;
    }

    @Override
    public Map<String, Object> selectCommentList(String reviewDate, String reviewId) throws Exception {
        List<Comment> comments = commentMapper.selectCommentList(reviewDate, reviewId);

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", comments);

        return  res;
    }

    @Override
    public Map<String, Object> insertComment(String reviewDate, String reviewId, String userId, String comment) throws Exception {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String temp = sdf.format(today).substring(11,22);
        String tempCommentId = temp.replaceAll(":","");
        String commentId = tempCommentId.replace(".","");

        sdf = new SimpleDateFormat("yyyyMMdd");

        String commentDate = sdf.format(today);

        commentMapper.insertComment(reviewDate, reviewId, commentId, userId, comment, commentDate);

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");

        return  res;
    }

    @Override
    public Map<String, Object> updateComment(String reviewDate, String reviewId, String commentId, String userId, String comment) throws Exception {
        Date today = new Date();
        SimpleDateFormat    sdf = new SimpleDateFormat("yyyyMMdd");
        String commentDate = sdf.format(today);

        commentMapper.updateComment(reviewDate, reviewId, commentId, userId, comment, commentDate);

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");
        return  res;
    }
}
