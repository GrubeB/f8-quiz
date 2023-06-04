package pl.app.game.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.app.core.api.entity.FetchableAllApi;
import pl.app.core.api.entity.FetchableByIdApi;
import pl.app.core.util.EntityLocationUriUtils;
import pl.app.game.domain.Game;
import pl.app.game.dto.CreateGame;
import pl.app.game.dto.SendRespond;
import pl.app.game.service.GameService;
import pl.app.quiz.domain.Question;

@RestController
@RequestMapping(GameApi.resourcePath)
@RequiredArgsConstructor
@Getter
public class GameApi implements
        FetchableAllApi<Long, Game>,
        FetchableByIdApi<Long, Game> {
    public static final String resourceName = "games";
    public static final String resourcePath = "/api/v1/" + resourceName;
    public final GameService service;

    @GetMapping(params = {"code"})
    ResponseEntity<Game> getGameByCode(@RequestParam String code) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getGameByCode(code));
    }

    @PostMapping
    ResponseEntity<Game> startGame(@RequestBody CreateGame dto, HttpServletRequest request) {
        Game game = service.startGame(dto.quizId());
        return ResponseEntity
                .created(EntityLocationUriUtils.createdEntityLocationURI(game.getId(), request.getRequestURI()))
                .body(game);
    }

    @PostMapping(path = "/{gameId}/stop")
    ResponseEntity<Game> stopGame(@PathVariable Long gameId) {
        Game game = service.stopGame(gameId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(game);
    }

    @GetMapping(path = "/{gameId}/participants/{participantId}/next-question")
    ResponseEntity<Question> getNextQuestion(@PathVariable Long gameId, @PathVariable Long participantId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getNextQuestion(gameId, participantId));
    }


    @PostMapping(path = "/{gameId}/participants/{participantId}/send-answer")
    ResponseEntity<Boolean> sendRespond(@PathVariable Long gameId, @PathVariable Long participantId, @RequestBody SendRespond dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.sendRespond(gameId, participantId, dto.questionId(), dto.answerIds()));
    }

}
