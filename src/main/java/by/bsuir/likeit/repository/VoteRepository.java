package by.bsuir.likeit.repository;

import by.bsuir.likeit.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByAnswerIdAndUserId(long answerId, long userId);
}
