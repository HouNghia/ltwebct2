package com.example.forum_web_ver_2.reponsitory;

import com.example.forum_web_ver_2.entity.Comment;
import com.example.forum_web_ver_2.entity.Reacts;
import com.example.forum_web_ver_2.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ReactReponsitory extends JpaRepository<Reacts,Integer> {
    List<Reacts> findReactsByTopic(Topic topic);
    List<Reacts> findReactsByComment(Comment comment);
    void removeAllByTopic_Id(Integer topicId);
    void removeAllByComment_Id(Integer commentId);
}
