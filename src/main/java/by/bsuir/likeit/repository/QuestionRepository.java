package by.bsuir.likeit.repository;

import by.bsuir.likeit.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findAllByAuthorId(long authorId, Pageable pageable);
    List<Question> findAllByAuthorId(long authorId);
    Page<Question> findAllByTopicId(long topicId, Pageable pageable);
    List<Question> findAllByTopicId(long topicId);
}
