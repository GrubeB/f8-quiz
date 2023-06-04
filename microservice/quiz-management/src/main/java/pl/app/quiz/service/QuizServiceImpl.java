package pl.app.quiz.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.app.core.security.auth.AuthenticationService;
import pl.app.core.security.auth.AuthorizationService;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;
import pl.app.quiz.repository.QuizRepository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Getter
@RequiredArgsConstructor
class QuizServiceImpl implements QuizService {
    private final QuizRepository repository;
    private final AuthenticationService authenticationService;
    @Override
    public void beforeCreateEntity(Quiz quiz) {
        Optional<Long> currentUserId = authenticationService.getCurrentUserId();
        currentUserId.ifPresent(quiz::setUserId);
    }
    @Override
    public void mergeEntity(Quiz target, Quiz source) {
        if (source != null) {
            target.setUserId( source.getUserId() );
            target.setName( source.getName() );
        }
    }
}
