package pl.app.core.service.entity;

import jakarta.validation.Valid;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;

@Validated
public interface SavableService<ID, ENTITY extends Persistable<ID>> {
    default ENTITY saveEntity(@Valid ENTITY entity) {
        beforeSaveEntity(entity);
        ENTITY savedEntity = getRepository().saveAndFlush(entity);
        afterSaveEntity(savedEntity);
        return savedEntity;
    }

    default void beforeSaveEntity(ENTITY entity) {
    }

    default void afterSaveEntity(ENTITY savedEntity) {
    }

    JpaRepository<ENTITY, ID> getRepository();
}
