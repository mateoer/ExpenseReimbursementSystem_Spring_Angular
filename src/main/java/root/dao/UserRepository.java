package root.dao;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import root.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);
	public User findByUsernameAndPassword(String username, String password);
	public User findByUsernameAndEmail(String username, String email);
	public User findByUsernameOrEmail(String username, String email);
	public User findByUserId(int userId);
	public User findByEmail(String email);
	public User findByPasswordResetToken(String resetToken);
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByUsername(@Param("username") String username);
	
}
