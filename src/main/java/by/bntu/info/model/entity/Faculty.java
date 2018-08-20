package by.bntu.info.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@Entity
public class Faculty {

    @Id
    private Long id;
    private String name;

    public Faculty(String name) {
        this.name = name;
    }
}
