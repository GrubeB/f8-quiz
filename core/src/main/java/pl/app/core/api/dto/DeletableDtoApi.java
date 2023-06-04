package pl.app.core.api.dto;


import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.app.core.api.BaseApi;
import pl.app.core.service.dto.DeletableDtoByIdService;

public interface DeletableDtoApi<ID, ENTITY extends Persistable<ID>> extends BaseApi {
    @DeleteMapping(byId)
    default ResponseEntity<ID> deleteById(@PathVariable ID id) {
        getService().deleteEntityById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(id);
    }

    DeletableDtoByIdService<ID, ENTITY> getService();
}
