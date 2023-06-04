package pl.app.core.service.entity;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import pl.app.core.exception.NotFoundException;

public interface DeletableByIdService<ID, ENTITY extends Persistable<ID>> {
    default void deleteEntityById(ID id) {
        Assert.notNull(id, "Id must not be null.");
        ENTITY existingEntity = getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException("Not found object with id: " + id));
        beforeDeleteEntity(id, existingEntity);
        getRepository().deleteById(id);
        afterDeleteEntity(id);
    }

    default void beforeDeleteEntity(ID id, ENTITY existingEntity) {
    }

    default void afterDeleteEntity(ID id) {
    }

    JpaRepository<ENTITY, ID> getRepository();
}