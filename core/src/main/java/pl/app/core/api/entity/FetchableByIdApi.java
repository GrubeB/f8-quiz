package pl.app.core.api.entity;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.app.core.api.BaseApi;
import pl.app.core.service.entity.FetchableByIdService;

public interface FetchableByIdApi<ID, ENTITY> extends BaseApi {
    @GetMapping(byId)
    default ResponseEntity<ENTITY> fetchById(@PathVariable ID id) {
        ENTITY entity = getService().fetchEntityById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entity);
    }

    FetchableByIdService<ID, ENTITY> getService();
}
