package by.bsuir.likeit.mapper;

import by.bsuir.likeit.dto.request.TopicRequest;
import by.bsuir.likeit.entity.Topic;
import by.bsuir.likeit.service.UserService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", expression = "java(userService.getUserByUsername(authorName))")
    @Mapping(target = "name", source = "title")
    @Mapping(target = "creationTime", expression = "java(java.time.LocalDateTime.now())")
    Topic toEntity(TopicRequest topicRequest, @Context UserService userService, @Context String authorName);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "name", source = "title")
    @Mapping(target = "creationTime", ignore = true)
    Topic updateTopic(TopicRequest topicRequest, @MappingTarget Topic topic);
}
