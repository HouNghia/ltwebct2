package com.example.forum_web_ver_2.service;

import com.example.forum_web_ver_2.entity.Comment;
import com.example.forum_web_ver_2.entity.Topic;
import com.example.forum_web_ver_2.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public interface ReactService {
    void saveReactTopic(String namereact, Topic topic, User user);
    void saveReactComment(String namereact, Comment comment, User user);
    int countReact(Topic topic);
    int countReactComment(Comment comment);
    void delete(Integer topicId);
}
