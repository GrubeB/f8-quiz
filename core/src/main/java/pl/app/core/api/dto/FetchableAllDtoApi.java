package pl.app.core.api.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.app.core.api.BaseApi;
import pl.app.core.service.dto.FetchableAllDtoService;

import java.util.List;

public interface FetchableAllDtoApi<ID, ENTITY, DTO> extends BaseApi {
    @GetMapping
    default ResponseEntity<List<DTO>> fetchAll() {
        List<DTO> dtoList = getService().fetchDtoAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dtoList);
    }

    FetchableAllDtoService<ID, ENTITY, DTO> getService();
}
