package root.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import root.model.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
