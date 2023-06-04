package pl.app.core.api.dto;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.app.core.api.BaseApi;
import pl.app.core.service.dto.FetchableDtoByIdService;

public interface FetchableDtoByIdApi<ID, ENTITY, DTO> extends BaseApi {
    @GetMapping(byId)
    default ResponseEntity<DTO> fetchById(@PathVariable ID id) {
        DTO dto = getService().fetchDtoById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    FetchableDtoByIdService<ID, ENTITY, DTO> getService();
}
