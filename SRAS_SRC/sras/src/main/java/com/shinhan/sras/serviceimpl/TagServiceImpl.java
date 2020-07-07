package com.shinhan.sras.serviceimpl;

import com.shinhan.sras.dao.TagDao;
import com.shinhan.sras.model.Tag;
import com.shinhan.sras.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public Map<String, Object> getAllTag() throws Exception {
        return tagDao.selectAllTag();
    }

    @Override
    public Map<String, Object> registerTag(Tag tag) throws Exception {
        return tagDao.insertTag(tag);
    }

    @Override
    public Map<String, Object> deleteTag(String tagContent) throws Exception {
        return tagDao.deleteTag(tagContent);
    }
}
