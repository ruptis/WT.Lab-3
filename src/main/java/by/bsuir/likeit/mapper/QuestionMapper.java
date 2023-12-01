package by.bsuir.likeit.mapper;

import by.bsuir.likeit.dto.request.QuestionRequest;
import by.bsuir.likeit.entity.Question;
import by.bsuir.likeit.service.TopicService;
import by.bsuir.likeit.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topic", expression = "java(topicService.getTopic(questionRequest.getTopicId()))")
    @Mapping(target = "author", expression = "java(userService.getUserByUsername(authorName))")
    @Mapping(target = "askTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "lastUpdateTime", expression = "java(java.time.LocalDateTime.now())")
    Question toEntity(QuestionRequest questionRequest,
                      @Context TopicService topicService,
                      @Context UserService userService,
                      @Context String authorName);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "topic", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "askTime", ignore = true)
    @Mapping(target = "lastUpdateTime", expression = "java(java.time.LocalDateTime.now())")
    Question updateEntity(QuestionRequest questionRequest,
                          @Context UserService userService,
                          @Context String authorName,
                          @MappingTarget Question question);
}
