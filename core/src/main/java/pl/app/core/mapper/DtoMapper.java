package pl.app.core.mapper;

public interface DtoMapper<ENTITY, DTO> extends
        DtoToEntityMapper<ENTITY, DTO>,
        EntityToDtoMapper<ENTITY, DTO>,
        MergeMapper<ENTITY> {
}
