package by.bsuir.likeit.service;


import by.bsuir.likeit.dto.request.AnswerRequest;
import by.bsuir.likeit.entity.Answer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AnswerService {

    Answer addAnswer(AnswerRequest answerRequest, String authorName);

    Answer getAnswer(long id);

    Page<Answer> getAllAnswers(int page, int pageSize);

    List<Answer> getAllAnswers();

    Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize);

    List<Answer> getAllAnswersByQuestion(long questionId);

    Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize);

    List<Answer> getAllAnswersByAuthor(long authorId);
}
