package pl.app.core.service.entity;

import org.springframework.data.domain.Persistable;
import org.springframework.util.Assert;
import pl.app.core.exception.NotFoundException;

public interface UpdatableService<ID, ENTITY extends Persistable<ID>> extends SavableService<ID, ENTITY> {
    default ENTITY update(ID id, ENTITY newEntity) {
        Assert.notNull(id, "Id must not be null.");
        ENTITY existingEntity = getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException("Not found object with id: " + id));
        beforeUpdateEntity(id, existingEntity, newEntity);
        mergeEntity(existingEntity, newEntity);
        ENTITY savedEntity = saveEntity(existingEntity);
        afterUpdateEntity(id, savedEntity, existingEntity);
        return savedEntity;
    }

    default void beforeUpdateEntity(ID id, ENTITY existingEntity, ENTITY newEntity) {
    }
    default void mergeEntity(ENTITY existingEntity, ENTITY newEntity) {

    }
    default void afterUpdateEntity(ID id, ENTITY savedEntity, ENTITY oldEntity) {
    }
}