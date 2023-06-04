package pl.app.quiz.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;
import pl.app.quiz.repository.QuestionRepository;
import pl.app.quiz.repository.QuizRepository;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@Getter
@RequiredArgsConstructor
class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;
    private final QuizService quizService;
    @Override
    public Question createEntity(Long quizId, Question question) {
        Question savedQuestion = repository.save(question);
        Quiz quiz = quizService.fetchEntityById(quizId);
        quiz.addQuestion(savedQuestion);
        quizService.saveEntity(quiz);
        return savedQuestion;
    }

    @Override
    public void mergeEntity(Question target, Question source) {
        if (source != null) {
            target.setType(source.getType());
            target.setContext(source.getContext());
            Set set;
            if (target.getAnswers() != null) {
                set = source.getAnswers();
                if (set != null) {
                    target.getAnswers().clear();
                    target.getAnswers().addAll(set);
                } else {
                    target.setAnswers((Set)null);
                }
            } else {
                set = source.getAnswers();
                if (set != null) {
                    target.setAnswers(new LinkedHashSet(set));
                }
            }

            target.setExplanation(source.getExplanation());
            target.setCategory(source.getCategory());
            target.setNumberOfPoints(source.getNumberOfPoints());
        }
    }
}
