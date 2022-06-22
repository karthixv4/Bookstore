package book.store.repos;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import book.store.model.Book;
import book.store.model.Users;
@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
	@Transactional
	@Modifying
	@Query("delete from Book b where b.bId like ?1 and fileName like ?2")
	public void deleteBookWithFile(Long id, String fileName);
	@Query("SELECT u FROM Book u WHERE u.bName LIKE %?1%" + " OR u.bDesc like %?1%")
	 public List<Book> findAll(String keyword);
}

