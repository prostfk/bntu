package by.bntu.info.repository;

import by.bntu.info.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    List<User> findAll();
    User findUserByUsername(String username);
    List<User> findUsersByRole(String role);

}
