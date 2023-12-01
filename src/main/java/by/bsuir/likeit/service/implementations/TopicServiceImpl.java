package by.bsuir.likeit.service.implementations;

import by.bsuir.likeit.dto.request.TopicRequest;
import by.bsuir.likeit.entity.Topic;
import by.bsuir.likeit.mapper.TopicMapper;
import by.bsuir.likeit.repository.TopicRepository;
import by.bsuir.likeit.service.TopicService;
import by.bsuir.likeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserService userService;
    private final TopicMapper topicMapper;

    @Override
    public Topic addTopic(TopicRequest topicRequest, String authorName) {
        return Optional.of(topicRequest)
                .map(topic -> topicMapper.toEntity(topic, userService, authorName))
                .map(topicRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public boolean deleteTopic(long id) {
        return topicRepository.findById(id)
                .map(topic -> {
                    topicRepository.delete(topic);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public Topic updateTopic(long id, TopicRequest topicRequest) {
        return topicRepository.findById(id)
                .map(topic -> topicMapper.updateTopic(topicRequest, topic))
                .map(topicRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public Topic getTopic(long id) {
        return topicRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Topic> getAllTopics(int page, int pageSize) {
        return topicRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}
