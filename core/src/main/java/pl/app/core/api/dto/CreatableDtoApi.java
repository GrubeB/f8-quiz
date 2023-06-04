package pl.app.core.api.dto;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.app.core.api.BaseApi;
import pl.app.core.dto.BaseDto;
import pl.app.core.service.dto.CreatableDtoService;
import pl.app.core.util.EntityLocationUriUtils;

public interface CreatableDtoApi<ID, ENTITY extends Persistable<ID>, DTO extends BaseDto<ID>, CreateDTO> extends BaseApi {
    @PostMapping
    default ResponseEntity<DTO> create(@RequestBody CreateDTO createDto, HttpServletRequest request) {
        DTO createdDto = getService().createEntityFromDto(createDto);
        return ResponseEntity
                .created(EntityLocationUriUtils.createdEntityLocationURI(createdDto.getId(), request.getRequestURI()))
                .body(createdDto);
    }

    CreatableDtoService<ID, ENTITY, DTO, CreateDTO> getService();
}
