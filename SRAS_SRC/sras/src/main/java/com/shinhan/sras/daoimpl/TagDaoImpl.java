package com.shinhan.sras.daoimpl;

import com.shinhan.sras.dao.TagDao;
import com.shinhan.sras.mapper.ReviewMapper;
import com.shinhan.sras.mapper.TagMapper;
import com.shinhan.sras.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagDaoImpl implements TagDao {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public Map<String, Object> selectAllTag() throws Exception {
        List<Tag> tags = tagMapper.selectTagList();

        //Test Data
        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", tags);
        return res;
    }

    @Override
    public Map<String, Object> insertTag(Tag tag)  throws Exception{
        tagMapper.insertTag(tag.getTagContent());

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");

        return  res;
    }

    @Override
    public Map<String, Object> deleteTag(String tagContent) throws Exception {

        tagMapper.deleteTag(tagContent);

        List<Tag> deleteTags = reviewMapper.selectDeleteTag("%"+tagContent+"%");

        if(!deleteTags.isEmpty() && deleteTags !=null) {
            for(int i=0; i< deleteTags.size(); i++) {
                if(deleteTags.get(i).getTagContent().contains(tagContent+",")) {
                    String beforeTag = deleteTags.get(i).getTagContent();
                    deleteTags.get(i).setTagContent(beforeTag.replaceAll(tagContent+",", ""));
                    String afterTag = deleteTags.get(i).getTagContent();
                    reviewMapper.deleteTag(beforeTag, afterTag);

                } else if(deleteTags.get(i).getTagContent().contains("," + tagContent)) {
                    String beforeTag = deleteTags.get(i).getTagContent();
                    deleteTags.get(i).setTagContent(beforeTag.replaceAll("," + tagContent, ""));
                    String afterTag = deleteTags.get(i).getTagContent();
                    reviewMapper.deleteTag(beforeTag, afterTag);
                }
            }
        }

        Map<String, Object> res = new HashMap<>();

        res.put("success", true);
        res.put("msg", "");

        res.put("data", "");

        return  res;
    }
}
