package pl.app.backend.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.app.backend.quiz.dto.Question;
import pl.app.backend.game.dto.CreateGame;
import pl.app.backend.game.dto.SendRespond;

import java.util.List;

@FeignClient(name = "gameServiceApi", url = "http://localhost:9007") //TODO
public interface GameServiceApi {

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.GET)
    List<Game> fetchAll();

    @RequestMapping(path = "/api/v1/games/{id}", method = RequestMethod.GET)
    Game fetch(@PathVariable Long id);

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.GET)
    Game getGameByCode(@RequestParam String code);

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.POST)
    Game startGame(@RequestBody CreateGame dto);

    @RequestMapping(path = "/api/v1/games/{gameId}/stop", method = RequestMethod.POST)
    Game stopGame(@PathVariable Long gameId);

    @RequestMapping(path = "/api/v1/games/{gameId}/participants/{participantId}/next-question", method = RequestMethod.POST)
    Question getNextQuestion(@PathVariable Long gameId, @PathVariable Long participantId);

    @RequestMapping(path = "/api/v1/games/{gameId}/participants/{participantId}/send-answer", method = RequestMethod.POST)
    ResponseEntity<Boolean> sendRespond(@PathVariable Long gameId, @PathVariable Long participantId, @RequestBody SendRespond dto);
}
