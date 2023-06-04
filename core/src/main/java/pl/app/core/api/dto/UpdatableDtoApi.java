package pl.app.core.api.dto;

import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.app.core.api.BaseApi;
import pl.app.core.service.dto.UpdatableDtoService;

public interface UpdatableDtoApi<ID, ENTITY extends Persistable<ID>, DTO, UpdateDTO> extends BaseApi {
    @PutMapping(byId)
    default ResponseEntity<DTO> update(@PathVariable ID id, @RequestBody UpdateDTO updateDto) {
        DTO updatedDto = getService().updateDto(id, updateDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedDto);
    }

    UpdatableDtoService<ID, ENTITY, DTO, UpdateDTO> getService();
}
