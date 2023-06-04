package pl.app.core.service.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.Assert;
import pl.app.core.exception.NotFoundException;

public interface FetchableByIdService<ID, ENTITY> {
    default ENTITY fetchEntityById(ID id) {
        Assert.notNull(id, "Id must not be null.");
        beforeFetchEntityById(id);
        ENTITY entity = getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException("Not found object with id: " + id));
        afterFetchEntityById(id, entity);
        return entity;
    }

    default void beforeFetchEntityById(ID id) {
    }

    default void afterFetchEntityById(ID id, ENTITY entity) {
    }

    JpaRepository<ENTITY, ID> getRepository();
}
