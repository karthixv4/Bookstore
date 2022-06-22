package book.store.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.store.model.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

	public Role findByName(String name);

}
