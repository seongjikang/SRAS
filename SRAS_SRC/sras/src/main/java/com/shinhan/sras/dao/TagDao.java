package com.shinhan.sras.dao;

import com.shinhan.sras.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface TagDao {
    Map<String, Object> selectAllTag() throws Exception;
    Map<String, Object> insertTag(Tag tag) throws Exception;
    Map<String, Object> deleteTag(String tagContent) throws Exception;
}
