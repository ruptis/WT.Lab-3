package by.bsuir.likeit.service;


import by.bsuir.likeit.entity.Vote;

public interface VoteService {
    void upvote(long answerId, long userId);

    void downvote(long answerId, long userId);

    void unvote(long answerId, long userId);

    Vote getVote(long answerId, long userId);
}
