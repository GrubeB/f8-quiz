package pl.app.core.service.entity;

import jakarta.validation.Valid;
import org.springframework.data.domain.Persistable;
import org.springframework.validation.annotation.Validated;


public interface CreatableService<ID, ENTITY extends Persistable<ID>>
        extends SavableService<ID, ENTITY> {
    default ENTITY createEntity(ENTITY entity) {
        beforeCreateEntity(entity);
        ENTITY savedEntity = saveEntity(entity);
        afterCreateEntity(savedEntity);
        return savedEntity;
    }

    default void beforeCreateEntity(ENTITY entity) {
    }

    default void afterCreateEntity(ENTITY savedEntity) {
    }
}
