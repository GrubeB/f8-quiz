package pl.app.core.api.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import pl.app.core.api.BaseApi;
import pl.app.core.service.entity.FetchableAllService;

import java.util.List;

public interface FetchableAllApi<ID, ENTITY> extends BaseApi {
    @GetMapping
    default ResponseEntity<List<ENTITY>> fetchAll() {
        List<ENTITY> entityList = getService().fetchEntityAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entityList);
    }

    FetchableAllService<ID, ENTITY> getService();
}
