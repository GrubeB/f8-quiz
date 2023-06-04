package pl.app.core.service.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.core.mapper.DtoMapper;
import pl.app.core.mapper.EntityToDtoMapper;
import pl.app.core.service.entity.FetchableAllService;

import java.util.List;
import java.util.stream.Collectors;

public interface FetchableAllDtoService<ID, ENTITY, DTO>
        extends FetchableAllService<ID, ENTITY> {
    default List<DTO> fetchDtoAll() {
        beforeFetchDtoAll();
        List<ENTITY> entityList = fetchEntityAll();
        List<DTO> dtoList = getMapper().mapEntityListToDtoList(entityList);
        afterFetchDtoAll(dtoList);
        return dtoList;
    }

    default void beforeFetchDtoAll() {
    }

    default void afterFetchDtoAll(List<DTO> dtoList) {
    }

    EntityToDtoMapper<ENTITY, DTO> getMapper();

//    JpaRepository<ENTITY, ID> getRepository();
}
