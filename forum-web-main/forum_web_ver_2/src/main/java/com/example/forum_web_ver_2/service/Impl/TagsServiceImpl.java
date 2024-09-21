package com.example.forum_web_ver_2.service.Impl;

import com.example.forum_web_ver_2.entity.Tags;
import com.example.forum_web_ver_2.reponsitory.TagsReponsitory;
import com.example.forum_web_ver_2.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {
    @Autowired
    private TagsReponsitory tagsReponsitory;
    @Override
    public List<Tags> getListTag() {
        return tagsReponsitory.findAll();
    }
}
