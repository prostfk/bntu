package by.bntu.info.repository;

import by.bntu.info.model.entity.Faculty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {

    List<Faculty> findAll();


}
