package by.bsuir.likeit.service.implementations;

import by.bsuir.likeit.dto.request.AnswerRequest;
import by.bsuir.likeit.entity.Answer;
import by.bsuir.likeit.mapper.AnswerMapper;
import by.bsuir.likeit.repository.AnswerRepository;
import by.bsuir.likeit.service.AnswerService;
import by.bsuir.likeit.service.QuestionService;
import by.bsuir.likeit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final UserService userService;
    private final QuestionService questionService;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public Answer addAnswer(AnswerRequest answerRequest, String authorName) {
        return Optional.of(answerRequest)
                .map(request -> answerMapper.toEntity(request, questionService, userService, authorName))
                .map(answerRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    public Answer getAnswer(long id) {
        return answerRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Answer> getAllAnswers(int page, int pageSize) {
        return answerRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    @Override
    public Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize) {
        return answerRepository.findAllByQuestionId(questionId, PageRequest.of(page, pageSize));
    }

    @Override
    public List<Answer> getAllAnswersByQuestion(long questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    @Override
    public Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize) {
        return answerRepository.findAllByAuthorId(authorId, PageRequest.of(page, pageSize));
    }

    @Override
    public List<Answer> getAllAnswersByAuthor(long authorId) {
        return answerRepository.findAllByAuthorId(authorId);
    }
}
