package book.store.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



import book.store.model.Book;
import book.store.model.Genre;
import book.store.repos.BookRepo;
import book.store.service.BookService;
import book.store.service.GenreService;

@Controller
public class BookController {
	public static String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	 
	    @Autowired
	    private BookRepo bookRepo;
	    @Autowired
	    private BookService bookService;
	    @Autowired
	    private GenreService genreService;

	 //Book things
    @GetMapping("/addBook")
 public String showAddProductForm(Model model) {
        model.addAttribute("book", new Book());

        return "addProduct";
    }
    //adding book to db
    @PostMapping("/saveBook")
	public String saving( @Valid Book book,final @RequestParam("file") MultipartFile file) 
			throws IOException {
		
			
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			String fileType = file.getContentType();
			

			// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			stream.write(file.getBytes());
			stream.close();

			
			book.setFileName(fileName);
			book.setFilePath(filePath);
			book.setFileType(fileType);
			
			bookService.addBook(book);
			
		
		return "redirect:/books";
	
	}
    //showing books
    @GetMapping("/books")
    public String listBooks(Model model,@Param("keyword") String keyword) {
        List<Book> listBooks = bookService.showAllBooks(keyword);
        model.addAttribute("listBooks", listBooks);
         
        return "books";
    }

	
	  @GetMapping("/edit/{id}") 
	  public String showUpdateBookForm(@PathVariable("id")
	  long id, Model model) { Book book = bookRepo.findById(id) .orElseThrow(() ->
	  new IllegalArgumentException("Invalid user Id:" + id));
	  
	  model.addAttribute("book", book); return "updateProduct"; }
	 
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") long id, Model model,@Valid Book book) {
        
            
        bookService.updateBook(id, book);
        return "redirect:/books";
    }
	/*
	 * @GetMapping("/delete/{id}") public String deleteBook(@PathVariable("id") long
	 * id, Model model) { bookService.removeBook(id);
	 * 
	 * 
	 * return "redirect:/books"; }
	 */
    @GetMapping("/delete/{id}/{deletedFileName}")
	public String removeFile(@PathVariable("id") Long id, @PathVariable("deletedFileName") String deletedFileName) {		
		
		String path = null;
		File file =null;
	
			path = uploadDirectory + "/" + deletedFileName;
			file = new File(path);
			if(file.exists()) {
				bookService.removeBookandFile(id, deletedFileName);
				return "redirect:/books";
			
			}else {
				bookService.removeBook(id);
			}
			return "adminPanel";
			
		
	}

	
	
	 
	 
    //Bringing genres 
    @GetMapping("/genres")
    public String listGenres(Model model) {
        List<Genre> listGenres = genreService.showAllGenre();
        model.addAttribute("genre", listGenres);
         
        return "genres";
    }
	
	  //Genre things
	  //Clicking adding
	  @GetMapping("/addGenre") 
	  public String showAddGenreForm(Model model) {
	  model.addAttribute("genre", new Genre());
	  
	  return "addGenre"; }
	  
	  //Clicking add button
	  //after chnaging the genre name clicking
	  @PostMapping("/updategenre") 
	  public String genrePost(@ModelAttribute("genre") Genre genre) {
	   
	  genreService.addGenre(genre);
	  return
	  "redirect:/genres";
  
	  }
	  //Updating
	  //on genres table page clicking update button
	  @GetMapping("/updateGenre/{id}")
	    public String addGenre(@PathVariable("id") Long id,Model model) {
	    	Optional<Genre> genre = genreService.getById(id); 
	    	if(genre.isPresent())
	    	{
	    		model.addAttribute("genre",genre.get());
	    		return "addGenre";
	    	}
				
	    	else  return"404";
	    	
	    }
	  
	
	  
	  @GetMapping("/deleteGenre/{id}")
	  public String deleteGenre(@PathVariable("id") long gId, Model model) {
	  genreService.removeGenre(gId); 
	  return "adminPanel"; }
}
