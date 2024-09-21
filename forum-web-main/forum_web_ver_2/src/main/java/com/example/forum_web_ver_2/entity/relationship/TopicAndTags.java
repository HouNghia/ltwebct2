package com.example.forum_web_ver_2.entity.relationship;

import com.example.forum_web_ver_2.entity.Tags;
import com.example.forum_web_ver_2.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic_and_tags")
public class TopicAndTags implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tags tags;
}
