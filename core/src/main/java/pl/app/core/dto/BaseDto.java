package pl.app.core.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@DTO
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto<ID> implements Serializable {
    protected ID id;
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
    public static <T> BaseDtoBuilder<?, ?, ?> builder() {
        return new BaseDtoBuilderImpl<T>();
    }
}