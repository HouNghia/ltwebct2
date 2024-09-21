package com.example.forum_web_ver_2.service;

import com.example.forum_web_ver_2.entity.Tags;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagsService {
    List<Tags> getListTag();
}
