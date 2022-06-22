package book.store.service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import book.store.model.Book;
import book.store.repos.BookRepo;

@Service
public class BookService {

	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
@Autowired
	private BookRepo bookRepo;

public List<Book> showAllBooks(String keyword){
	if(keyword !=null) {
		return bookRepo.findAll(keyword);
	}
	return bookRepo.findAll();
}
public Optional<Book> showAllBooksById(Long bId){
	return bookRepo.findById(bId);
}
public Book addBook(Book book) {
	
	return bookRepo.save(book);
	
}
public void removeBookandFile(Long bId,String fileName) {
	try {
		if (bId != 0 && fileName != null) {
			bookRepo.deleteBookWithFile(bId, fileName);
		
			String path = uploadDirectory + "/" + fileName;
			System.out.println("Path=" + path);
			File fileToDelete = new File(path);
			fileToDelete.delete();
			
		}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
}
public void removeBook(Long bId) {
	bookRepo.deleteById(bId);
}
public void updateBook(Long bId,Book book) {
	bookRepo.findById(bId);
	bookRepo.deleteById(bId);
	bookRepo.save(book);
}
public int bookpriceafterdiscount(Book book) {
	int beforedisc = book.getbPrice();
	int afterdisc = book.getbDiscount();
	int applydisc = beforedisc - afterdisc;
	return applydisc;
	
}
}