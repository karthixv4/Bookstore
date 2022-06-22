package book.store.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import book.store.model.Genre;

public interface GenreRepo extends JpaRepository<Genre, Long> {

}
