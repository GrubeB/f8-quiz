package pl.app.core.api.entity;

import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.app.core.api.BaseApi;
import pl.app.core.service.entity.UpdatableService;

public interface UpdatableApi<ID, ENTITY extends Persistable<ID>> extends BaseApi {
    @PutMapping(byId)
    default ResponseEntity<ENTITY> update(@PathVariable ID id, @RequestBody ENTITY entity) {
        ENTITY savedEntity = getService().update(id, entity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedEntity);
    }

    UpdatableService<ID, ENTITY> getService();
}
