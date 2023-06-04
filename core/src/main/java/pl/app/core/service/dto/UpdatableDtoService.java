package pl.app.core.service.dto;

import jakarta.validation.Valid;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.validation.annotation.Validated;
import pl.app.core.exception.NotFoundException;
import pl.app.core.mapper.DtoMapper;
import pl.app.core.mapper.DtoToEntityMapper;
import pl.app.core.service.entity.UpdatableService;

@Validated
public interface UpdatableDtoService<ID, ENTITY extends Persistable<ID>, DTO, UpdateDTO>
        extends UpdatableService<ID, ENTITY> {
    default DTO updateDto(ID id, @Valid UpdateDTO updateDTO) {
        beforeUpdateDto(id, updateDTO);
        ENTITY existingEntity = getRepository().findById(id)
                .orElseThrow(() -> new NotFoundException("Not found object with id: " + id));
        ENTITY sourceEntity = getUpdateMapper().mapDtoToEntity(updateDTO);
        ENTITY mergedEntity = getMapper().mergeEntityWithAnotherEntity(existingEntity, sourceEntity);
        ENTITY updatedEntity = update(id, mergedEntity);
        DTO dto = getMapper().mapEntityToDto(updatedEntity);
        afterUpdateDto(id, dto);
        return dto;
    }

    default void beforeUpdateDto(ID id, UpdateDTO updateDTO) {
    }

    default void afterUpdateDto(ID id, DTO dto) {
    }

    DtoMapper<ENTITY, DTO> getMapper();

    DtoToEntityMapper<ENTITY, UpdateDTO> getUpdateMapper();

    JpaRepository<ENTITY, ID> getRepository();
}
