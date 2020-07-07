package com.shinhan.sras.mapper;

import com.shinhan.sras.model.Tag;

import java.util.List;

public interface TagMapper {
    public List<Tag> selectTagList() throws Exception;
    public void insertTag(String tagContent) throws Exception;
    public void deleteTag(String tagContent) throws Exception;
}
