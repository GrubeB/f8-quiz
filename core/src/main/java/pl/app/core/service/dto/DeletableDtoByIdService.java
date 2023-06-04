package pl.app.core.service.dto;

import org.springframework.data.domain.Persistable;
import pl.app.core.service.entity.DeletableByIdService;

public interface DeletableDtoByIdService<ID, ENTITY extends Persistable<ID>>
        extends DeletableByIdService<ID, ENTITY> {
    default void deleteDtoById(ID id) {
        beforeDeleteDtoById(id);
        deleteEntityById(id);
        afterDeleteDtoById(id);
    }

    default void beforeDeleteDtoById(ID id) {
    }

    default void afterDeleteDtoById(ID id) {
    }
}
