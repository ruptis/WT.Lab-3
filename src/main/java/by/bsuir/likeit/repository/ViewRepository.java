package by.bsuir.likeit.repository;

import by.bsuir.likeit.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    Optional<View> findByQuestionIdAndUserId(long questionId, long userId);
}
