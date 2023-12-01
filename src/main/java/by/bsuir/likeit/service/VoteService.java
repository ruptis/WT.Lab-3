package by.bsuir.likeit.service;


import by.bsuir.likeit.entity.Vote;

import java.util.Optional;

public interface VoteService {
    void upvote(long answerId, String username);

    void downvote(long answerId, String username);

    void unvote(long answerId, String username);

    Optional<Vote> getVote(long answerId, String username);
}
