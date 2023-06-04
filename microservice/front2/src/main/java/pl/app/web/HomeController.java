package pl.app.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.app.backend.game.Game;
import pl.app.backend.game.GameServiceApi;
import pl.app.backend.game.Participant;
import pl.app.backend.game.ParticipantServiceApi;
import pl.app.backend.game.dto.CreateParticipant;
import pl.app.backend.game.dto.SendRespond;
import pl.app.backend.quiz.QuestionServiceApi;
import pl.app.backend.quiz.QuizServiceApi;
import pl.app.backend.quiz.dto.Answer;
import pl.app.backend.quiz.dto.Question;
import pl.app.backend.quiz.dto.QuestionType;
import pl.app.backend.quiz.dto.Quiz;
import pl.app.config.thymeleaf.ModelAndViewService;
import pl.app.web.dto.JoinGame;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping(HomeController.controllerPath)
@RequiredArgsConstructor
public class HomeController {
    public static final String controllerPath = "";
    private final GameServiceApi gameServiceApi;
    private final QuestionServiceApi questionServiceApi;
    private final ParticipantServiceApi participantServiceApi;

    @GetMapping(path = {"", "/"})
    ModelAndView home() {
        ModelAndView model = ModelAndViewService.clean("home");
        model.addObject("joinGame", new JoinGame());
        return model;
    }

    @PostMapping(path = controllerPath + "/join")
    String joinGame(Model model, @Valid JoinGame joinGame, Errors errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            return "redirect:/";
        }
        Game game = gameServiceApi.getGameByCode(joinGame.getCode());
        Participant participant = participantServiceApi.create(new CreateParticipant(joinGame.getCode(), joinGame.getName()));

        attributes.addFlashAttribute("toastMessage", "Joined!");
        attributes.addFlashAttribute("toastClass", "success");

        return "redirect:/games/" + game.getId() + "/participants/" + participant.getId();
    }

    @GetMapping(path = "/games/{gameId}/participants/{participantId}")
    ModelAndView game(@PathVariable Long gameId, @PathVariable Long participantId) {
        ModelAndView model = ModelAndViewService.clean("question");
        Game game = gameServiceApi.fetch(gameId);
        Participant participant = participantServiceApi.fetch(participantId);
        Question nextQuestion = gameServiceApi.getNextQuestion(gameId, participantId);
        if (nextQuestion == null) {
            return ModelAndViewService.clean("redirect:/games/" + gameId + "/participants/" + participantId + "/end");
        }
        nextQuestion.getAnswers().forEach(answer -> answer.setCorrect(false));
        model.addObject("question", nextQuestion);
        model.addObject("game", game);
        model.addObject("participant", participant);
        return model;
    }

    @PostMapping(path = "/games/{gameId}/participants/{participantId}/question/{questionId}")
    String answer(@PathVariable Long gameId, @PathVariable Long participantId, @PathVariable Long questionId,
                  Model model, Question q, Errors errors, RedirectAttributes attributes) {
        Set<Long> answerIds = q.getAnswers()
                .stream().filter(Answer::getCorrect)
                .map(Answer::getId)
                .collect(Collectors.toSet());
        Game game = gameServiceApi.fetch(gameId);
        Question question = questionServiceApi.fetch(game.getQuiz().getId(), questionId);
        Participant participant = participantServiceApi.fetch(participantId);
        if(question.getType().equals(QuestionType.SINGLE_CHOICE_ANSWER) && answerIds.size() != 1){
            question.getAnswers().forEach(answer -> answer.setCorrect(false));
            model.addAttribute("question", question);
            model.addAttribute("game", game);
            model.addAttribute("participant", participant);
            return "question";
        }
// TODO


        Boolean booleanResponseEntity = gameServiceApi.sendRespond(gameId, participantId, new SendRespond(questionId, answerIds));

        if (booleanResponseEntity) {
            attributes.addFlashAttribute("toastMessage", "Correct!");
            attributes.addFlashAttribute("toastClass", "success");
        } else {
            attributes.addFlashAttribute("toastMessage", "Incorrect!");
            attributes.addFlashAttribute("toastClass", "error");
        }

        return "redirect:/games/" + gameId + "/participants/" + participantId;
    }

    @GetMapping(path = "/games/{gameId}/participants/{participantId}/end")
    ModelAndView end(@PathVariable Long gameId, @PathVariable Long participantId) {
        ModelAndView model = ModelAndViewService.clean("end");
        Game game = gameServiceApi.fetch(gameId);
        Participant participant = participantServiceApi.fetch(participantId);
        Double scoreMax = game.getQuiz().getQuestions().stream().mapToDouble(Question::getNumberOfPoints).sum();
        model.addObject("game", game);
        model.addObject("participant", participant);
        model.addObject("scoreMax", scoreMax);
        return model;
    }
}
