package by.bntu.info.repository;

import by.bntu.info.model.entity.News;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {

    List<News> findAll();
    News findNewsById(Long id);
    List<News> findNewsByType(String type);
//    @Query("SELECT n FROM news n WHERE n.faculty_id=(:facultyId)")
//    List<News> findNewsByFaculty_id(@Param("facultyId") Long facultyId);


//    @Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
//    public List<Person> find(@Param("lastName") String lastName);

}
