package pl.app.core.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class IdentityValueObject<ID extends Serializable> implements Persistable<ID>{
    private ID id;

    @Override
    public ID getId() {
        return id;
    }
    void setId(ID id){
        this.id = id;
    }
    @Override
    public boolean isNew() {
        return getId() == null;
    }
}
