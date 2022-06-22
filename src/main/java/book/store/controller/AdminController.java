package book.store.controller;





import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import book.store.model.Book;
import book.store.model.BookV;
import book.store.model.Genre;
import book.store.model.Role;
import book.store.model.UserV;
import book.store.model.Users;
import book.store.repos.BookRepo;
import book.store.repos.RoleRepo;
import book.store.repos.UserRepo;
import book.store.service.BookService;
import book.store.service.GenreService;
import book.store.service.UserService;

@Controller
public class AdminController {
	
	
	 @Autowired
	    private UserRepo userRepo;
	    @Autowired
	    private BookRepo bookRepo;
	    @Autowired
	    private BookService bookService;
	    @Autowired
	    private UserService userService;
	    @Autowired
	    private GenreService genreService;
	    @Autowired
	    private RoleRepo roleRepo;

	    @GetMapping("/adminPanel")
	    public String viewAdminPage() {
	        return "adminPanel";
	    }
	    //activts on clicking users in admin panel; activate1 
	    @GetMapping("/users")
	    public String listUsers(Model model, @Param("keyword") String keyword) {
	   
	        List<Users> listUsers = userService.showAllUsers(keyword);
	        model.addAttribute("listUsers", listUsers);
	         
	        return "users";
	    }
//on users table clicking update
	    @GetMapping("/editUser/{id}") 
	   	    public String showUpdateForm(@PathVariable("id")
	   		  long id, Model model) 
	   	    { 
	    	Optional<Users> user= userService.getUsersId(id);
	    	
	    	if(user.isPresent())
	    	{
	    		model.addAttribute("user",user.get());
	    		return "update_user";
	    	}
				
	    	else  return"404";
	
	   	
	   		  }
		 
			
			  @PostMapping("/updateUser") 
			  public String editing(@ModelAttribute("user")Users user) {
				  
				  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		    	    String encodedPassword = passwordEncoder.encode(user.getPassword());
		    	    user.setPassword(encodedPassword);
		    	    Role userRole= roleRepo.findByName("user");
		    		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
				  userService.addUser(user);
			
			  
			  return "redirect:/users"; 
			  }
			 
	    @GetMapping("/deleteUser/{id}")
	    public String deleteUser(@PathVariable("id") long id, Model model) {
	       userService.removeUser(id);
	          
	       
	        return "redirect:/users";
	    }
	    @GetMapping("/userlanding")
	   public String userlanding() {
	    	return "user_landing";
	    }
		 
		 
	    
}
