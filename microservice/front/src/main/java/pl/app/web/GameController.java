package pl.app.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.app.backend.game.Game;
import pl.app.backend.game.GameServiceApi;
import pl.app.backend.game.dto.CreateGame;
import pl.app.backend.quiz.QuizServiceApi;
import pl.app.backend.quiz.dto.Quiz;
import pl.app.config.thymeleaf.ModelAndViewService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    public static final String controllerPath = "games";
    private final GameServiceApi gameServiceApi;
    private final QuizServiceApi quizServiceApi;

    @GetMapping(path = controllerPath)
    ModelAndView viewAllView() {
        List<Game> games = gameServiceApi.fetchAll();
        ModelAndView model = ModelAndViewService.clean("games");
        model.addObject("games", games);
        return model;
    }

    @GetMapping(path = controllerPath + "/{gameId}")
    ModelAndView viewOneView(@PathVariable Long gameId) {
        Game game = gameServiceApi.fetch(gameId);
        ModelAndView model = ModelAndViewService.clean("game");
        model.addObject("game", game);
        model.addObject("participants", game.getParticipants());
        return model;
    }

    @GetMapping(path = controllerPath + "/new")
    ModelAndView createNewView() {
        List<Quiz> quizzes = quizServiceApi.fetchAll();
        ModelAndView model = ModelAndViewService.clean("start-games");
        model.addObject("quizzes", quizzes);
        return model;
    }

    @GetMapping(path = controllerPath + "/start/{quizId}")
    String startGame(@PathVariable(required = false) Long quizId, RedirectAttributes attributes) {
        gameServiceApi.create(new CreateGame(quizId));
        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");
        return "redirect:/games";
    }

    @GetMapping(path = controllerPath + "/{gameId}/stop")
    String stopGame(@PathVariable(required = false) Long gameId, RedirectAttributes attributes) {
        Game fetch = gameServiceApi.fetch(gameId);
        fetch.setEnded(true);
        gameServiceApi.update(gameId,fetch);

        attributes.addFlashAttribute("toastMessage", "Success!");
        attributes.addFlashAttribute("toastClass", "success");
        return "redirect:/games";
    }
}
