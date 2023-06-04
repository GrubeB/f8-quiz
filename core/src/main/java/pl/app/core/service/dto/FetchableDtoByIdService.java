package pl.app.core.service.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.app.core.mapper.DtoMapper;
import pl.app.core.mapper.EntityToDtoMapper;
import pl.app.core.service.entity.FetchableByIdService;

public interface FetchableDtoByIdService<ID, ENTITY, DTO>
        extends FetchableByIdService<ID, ENTITY> {
    default DTO fetchDtoById(ID id) {
        beforeFetchDtoById(id);
        ENTITY entity = fetchEntityById(id);
        DTO dto = getMapper().mapEntityToDto(entity);
        afterFetchDtoById(id, dto);
        return dto;
    }

    default void beforeFetchDtoById(ID id) {
    }

    default void afterFetchDtoById(ID id, DTO dto) {
    }

    EntityToDtoMapper<ENTITY, DTO> getMapper();
}
