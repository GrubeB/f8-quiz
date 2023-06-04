package pl.app.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
