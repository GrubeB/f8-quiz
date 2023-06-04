package pl.app.core.mapper;

import org.mapstruct.*;

@MapperConfig(
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        builder = @Builder(
                disableBuilder = true
        )
)
public interface MapStructConfig {
}
