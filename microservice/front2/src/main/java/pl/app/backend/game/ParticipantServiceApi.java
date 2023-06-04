package pl.app.backend.game;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.app.backend.game.dto.CreateParticipant;

import java.util.List;

@FeignClient(name = "participantServiceApi", url = "http://localhost:9007") //TODO
public interface ParticipantServiceApi {

    @RequestMapping(path = "/api/v1/participants", method = RequestMethod.GET)
    List<Participant> fetchAll();

    @RequestMapping(path = "/api/v1/participants/{id}", method = RequestMethod.GET)
    Participant fetch(@PathVariable Long id);

    @RequestMapping(path = "/api/v1/participants", method = RequestMethod.POST)
    Participant create(@RequestBody CreateParticipant dto);
}
