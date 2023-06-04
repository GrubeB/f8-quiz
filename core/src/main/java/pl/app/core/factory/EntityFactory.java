package pl.app.core.factory;

public interface EntityFactory<ENTITY, DTO> {
    ENTITY buildEntityFromDto(DTO dto);
}
