package by.bntu.info.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialty {

    @Id
    private Long id;
    private String name;
    private Long number;
    private Long facultyId;


}
