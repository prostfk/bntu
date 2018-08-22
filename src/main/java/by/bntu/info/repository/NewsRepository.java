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

    List<News> findNewsByFacultyId(Long facultyId);

    List<News> findNewsByFacultyIdOrderByIdDesc(Long facultyId);

    List<News> findAllByIdIsNotNullOrderByIdDesc();

}
