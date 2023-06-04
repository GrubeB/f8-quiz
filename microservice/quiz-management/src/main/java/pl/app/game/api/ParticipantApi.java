package pl.app.game.api;

import feign.Response;
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
import pl.app.game.domain.Participant;
import pl.app.game.dto.CreateParticipant;
import pl.app.game.service.GameService;
import pl.app.game.service.ParticipantService;
import pl.app.quiz.domain.Question;

import java.util.Set;

@RestController
@RequestMapping(ParticipantApi.resourcePath)
@RequiredArgsConstructor
@Getter
public class ParticipantApi implements
        FetchableAllApi<Long, Participant>,
        FetchableByIdApi<Long, Participant> {
    public static final String resourceName = "participants";
    public static final String resourcePath = "/api/v1/" + resourceName;
    public final ParticipantService service;

    @PostMapping
    ResponseEntity<Participant> create(@RequestBody CreateParticipant dto, HttpServletRequest request){
        Participant participant = service.create(dto.gameCode(), dto.name());
        return ResponseEntity
                .created(EntityLocationUriUtils.createdEntityLocationURI(participant.getId(), request.getRequestURI()))
                .body(participant);
    }
}