package by.bsuir.likeit.service.implementations;

import by.bsuir.likeit.entity.Vote;
import by.bsuir.likeit.repository.VoteRepository;
import by.bsuir.likeit.service.AnswerService;
import by.bsuir.likeit.service.UserService;
import by.bsuir.likeit.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final AnswerService answerService;
    private final UserService userService;
    private final VoteRepository voteRepository;

    @Override
    public void upvote(long answerId, String username) {
        long userId = getUserId(username);
        unvote(answerId, username);

        Vote vote = Vote.builder()
                .answer(answerService.getAnswer(answerId))
                .user(userService.getUserById(userId))
                .value(1)
                .build();

        voteRepository.saveAndFlush(vote);
    }

    @Override
    public void downvote(long answerId, String username) {
        long userId = getUserId(username);
        unvote(answerId, username);

        Vote vote = Vote.builder()
                .answer(answerService.getAnswer(answerId))
                .user(userService.getUserById(userId))
                .value(-1)
                .build();

        voteRepository.saveAndFlush(vote);
    }

    @Override
    public void unvote(long answerId, String username) {
        long userId = getUserId(username);
        voteRepository.findByAnswerIdAndUserId(answerId, userId)
                .ifPresent(voteRepository::delete);
    }

    @Override
    public Optional<Vote> getVote(long answerId, String username) {
        long userId = getUserId(username);
        return voteRepository.findByAnswerIdAndUserId(answerId, userId);
    }

    private long getUserId(String username) {
        return userService.getUserByUsername(username).getId();
    }
}
