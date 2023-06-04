package pl.app.backend.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import pl.app.backend.game.dto.CreateGame;
import pl.app.backend.game.dto.Game;
import pl.app.backend.game.dto.SendRespond;
import pl.app.backend.quiz.dto.Question;

import java.util.List;

@FeignClient(name = "gameServiceApi", url = "http://localhost:9007") //TODO
public interface GameServiceApi {

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.GET)
    List<Game> fetchAll();

    @RequestMapping(path = "/api/v1/games/{id}", method = RequestMethod.GET)
    Game fetch(@PathVariable Long id);

    @RequestMapping(path = "/api/v1/games/{id}", method = RequestMethod.PUT)
    Game update(@PathVariable Long id, @RequestBody Game entity);

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.GET)
    Game getGameByCode(@RequestParam String code);

    @RequestMapping(path = "/api/v1/games", method = RequestMethod.POST)
    Game create(@RequestBody CreateGame dto);

    @RequestMapping(path = "/api/v1/games/{gameId}/participants/{participantId}/questions", method = RequestMethod.GET)
    Question getNextQuestion(@PathVariable Long gameId, @PathVariable Long participantId,@RequestParam Boolean next);

    @RequestMapping(path = "/api/v1/games/{gameId}/participants/{participantId}/answers", method = RequestMethod.POST)
    Boolean sendRespond(@PathVariable Long gameId, @PathVariable Long participantId, @RequestBody SendRespond dto);
}
