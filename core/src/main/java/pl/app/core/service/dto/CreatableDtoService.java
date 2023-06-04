package pl.app.core.service.dto;

import jakarta.validation.Valid;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import pl.app.core.factory.EntityFactory;
import pl.app.core.mapper.DtoMapper;
import pl.app.core.mapper.DtoToEntityMapper;
import pl.app.core.mapper.EntityToDtoMapper;
import pl.app.core.service.entity.CreatableService;

@Validated
public interface CreatableDtoService<ID, ENTITY extends Persistable<ID>, DTO, CreateDTO>
        extends CreatableService<ID, ENTITY> {
    default DTO createEntityFromDto(@Valid CreateDTO createDTO) {
        beforeCreateDto(createDTO);
        ENTITY entity = getFactory().buildEntityFromDto(createDTO);
        ENTITY createdEntity = createEntity(entity);
        DTO dto = getMapper().mapEntityToDto(createdEntity);
        afterCreateDto(dto);
        return dto;
    }

    default void beforeCreateDto(CreateDTO createDTO) {
    }

    default void afterCreateDto(DTO dto) {
    }
    EntityToDtoMapper<ENTITY, DTO> getMapper();
    EntityFactory<ENTITY, CreateDTO> getFactory();
}
