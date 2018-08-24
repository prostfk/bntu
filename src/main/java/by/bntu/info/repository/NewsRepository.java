package by.bntu.info.repository;

import by.bntu.info.model.entity.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {

    List<News> findAll();

    News findNewsById(Long id);

    List<News> findNewsByType(String type);

    List<News> findNewsByFacultyId(Long facultyId);

    List<News> findNewsByFacultyIdOrderByIdDesc(Long facultyId);

    List<News> findAllByIdIsNotNullOrderByIdDesc();

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "UPDATE news SET title = ?1, content = ?2, path = ?3, faculty_id=?4, type=?5 WHERE id=?6",nativeQuery = true)
    void update(String title, String content, String path, Long facultyId, String type, Long id);

}
