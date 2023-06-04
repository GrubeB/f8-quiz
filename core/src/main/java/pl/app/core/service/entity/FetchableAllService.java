package pl.app.core.service.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FetchableAllService<ID, ENTITY> {
    default List<ENTITY> fetchEntityAll() {
        beforeFetchEntityAll();
        List<ENTITY> entityList = getRepository().findAll();
        afterFetchEntityAll(entityList);
        return entityList;

    }

    default void beforeFetchEntityAll() {
    }

    default void afterFetchEntityAll(List<ENTITY> entityList) {
    }

    JpaRepository<ENTITY, ID> getRepository();
}
