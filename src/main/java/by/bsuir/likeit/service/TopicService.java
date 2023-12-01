package by.bsuir.likeit.service;


import by.bsuir.likeit.dto.request.TopicRequest;
import by.bsuir.likeit.entity.Topic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicService {
    Topic addTopic(TopicRequest topicRequest, String authorName);

    boolean deleteTopic(long id);

    Topic updateTopic(long id, TopicRequest topicRequest);

    Topic getTopic(long id);

    Page<Topic> getAllTopics(int page, int pageSize);

    List<Topic> getAllTopics();
}
