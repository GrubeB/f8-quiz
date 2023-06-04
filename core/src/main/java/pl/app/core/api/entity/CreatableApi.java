package pl.app.core.api.entity;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Persistable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.app.core.api.BaseApi;
import pl.app.core.service.entity.CreatableService;
import pl.app.core.util.EntityLocationUriUtils;

public interface CreatableApi<ID, ENTITY extends Persistable<ID>> extends BaseApi {
    @PostMapping
    default ResponseEntity<ENTITY> create(@RequestBody ENTITY entity, HttpServletRequest request) {
        ENTITY savedEntity = getService().createEntity(entity);
        return ResponseEntity
                .created(EntityLocationUriUtils.createdEntityLocationURI(savedEntity.getId(), request.getRequestURI()))
                .body(savedEntity);
    }

    CreatableService<ID, ENTITY> getService();
}
