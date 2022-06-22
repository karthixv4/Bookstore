package book.store.repos;

import book.store.model.Users;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
	 @Query("SELECT u FROM Users u WHERE u.email = ?1")
	    public Users findByEmail(String email);
	 
	 @Query("SELECT u FROM Users u WHERE u.firstName LIKE %?1%" + " OR u.lastName LIKE %?1%" + " OR u.Address LIKE %?1%")
	 public List<Users> findAll(String keyword);
	     
}
