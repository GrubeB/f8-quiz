package pl.app.game.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.app.game.domain.Game;
import pl.app.game.domain.Participant;
import pl.app.game.domain.Score;
import pl.app.game.repository.GameRepository;
import pl.app.game.repository.ParticipantRepository;
import pl.app.game.repository.ScoreRepository;
import pl.app.quiz.domain.Answer;
import pl.app.quiz.domain.Question;
import pl.app.quiz.domain.Quiz;
import pl.app.quiz.service.QuizService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final ParticipantRepository participantRepository;
    private final QuizService quizService;

    @Override
    public void mergeEntity(Game existingEntity, Game newEntity) {
        existingEntity.setEnded(newEntity.getEnded());
        existingEntity.setQuizCode(newEntity.getQuizCode());
    }

    @Override
    public Game getGameByCode(String code) {
        return repository.findByQuizCodeIgnoreCase(code).orElseThrow();
    }

    @Override
    public Game create(Long quizId) {
        Quiz quiz = quizService.fetchEntityById(quizId);
        Game game = new Game(UUID.randomUUID().toString().substring(0, 5).toUpperCase(), quiz, new HashSet<>(),false);
        return repository.save(game);
    }

    @Override
    public Question getNextQuestion(Long gameId, Long participantId) {
        Game game = repository.findById(gameId).orElseThrow();
        Participant participant = game.getParticipants().stream()
                .filter(part -> Objects.equals(part.getId(), participantId))
                .findFirst().orElseThrow();
        Quiz quiz = game.getQuiz();
        try {
            List<Question> collect = quiz.getQuestions().stream()
                    .sorted(Comparator.comparing(Question::getId))
                    .toList();
            return collect.get(participant.getNumberOfResponds());
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }
    }

    @Override
    public boolean sendRespond(Long gameId, Long participantId, Long questionId, Set<Long> answerIds) {
        Game game = repository.findById(gameId).orElseThrow();
        Participant participant = game.getParticipants().stream()
                .filter(part -> Objects.equals(part.getId(), participantId))
                .findFirst().orElseThrow();
        Question question = game.getQuiz().getQuestions().stream()
                .filter(q -> Objects.equals(q.getId(), questionId))
                .findFirst().orElseThrow();
        boolean correctAnswer = question.getAnswers().stream()
                .allMatch(answer ->
                        (answerIds.contains(answer.getId()) && answer.getCorrect())
                        || (!answerIds.contains(answer.getId()) && !answer.getCorrect())
                );
        if(correctAnswer){
            participant.addPoints(question.getNumberOfPoints());
        }
        participant.incrementNumberOfResponds();
        participantRepository.save(participant);
        return correctAnswer;
    }
}
