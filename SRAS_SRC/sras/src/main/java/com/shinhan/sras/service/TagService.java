package com.shinhan.sras.service;

import com.shinhan.sras.model.Tag;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TagService {
    Map<String, Object> getAllTag() throws Exception;
    Map<String, Object> registerTag(Tag tag) throws Exception;
    Map<String, Object> deleteTag(String tagContent) throws Exception;
}
