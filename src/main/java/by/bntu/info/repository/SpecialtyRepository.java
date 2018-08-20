package by.bntu.info.repository;

import by.bntu.info.model.entity.Faculty;
import by.bntu.info.model.entity.Specialty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {

    List<Specialty> findAll();
    List<Specialty> findSpecialtiesByFacultyId(Long id);


}
