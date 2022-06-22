package book.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.store.model.Book;
import book.store.model.Genre;
import book.store.repos.GenreRepo;

@Service
public class GenreService {
	@Autowired
private GenreRepo genreRepo;

public List<Genre> showAllGenre(){
	return genreRepo.findAll();
}
public void addGenre(Genre genre) {
	genreRepo.save(genre);
}
public void removeGenre(Long gId) {
	genreRepo.deleteById(gId);
}
public Optional<Genre> getById(Long id) {
	return genreRepo.findById(id);
}
}
