package book.store.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import book.store.cart.CartRole;
import book.store.model.Book;
import book.store.model.Role;
import book.store.model.Users;
import book.store.repos.BookRepo;
import book.store.repos.RoleRepo;
import book.store.repos.UserRepo;
import book.store.service.BookService;
import book.store.service.UserService;


@Controller
public class AppController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private BookService bookService;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String viewHomePage() {
        return "login";
    }
    @GetMapping("/myAccount")
    public String myAccount() {
        return "user_landing";
    }
    @GetMapping("/index")
    public String viewIndexPage() {
        return "index";
    }
    @GetMapping("/home")
    public String viewIndexPage(Model model,@Param("keyword") String keyword) {
    	List<Book> listBooks = bookService.showAllBooks(keyword);
        model.addAttribute("listBooks", listBooks);
        return "home";
    }
    @GetMapping("/in")
    public String viewUserLandingPage() {
        return "user_landing";
    }
    @GetMapping("/login")
	public ModelAndView showlogin() {
		ModelAndView modelAndView = new ModelAndView();
		CartRole.cart.clear();
		modelAndView.setViewName("login");
		return modelAndView;
	}
    @GetMapping("/register")
    public String showRegistrationForm(Model model)  {
    	
        model.addAttribute("users", new Users());

        return "register";
    }
    @PostMapping("/process_register")
    public String processRegister(Users user,Model model) {
    	String email = user.getEmail();
    	
    	if(userRepo.findByEmail(email) != null) {
    		model.addAttribute("exist","An account already exists with this Email please Login");
    		return "register";
    		
    	} else {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	    String encodedPassword = passwordEncoder.encode(user.getPassword());
    	    user.setPassword(encodedPassword);
    	    Role userRole= roleRepo.findByName("user");
    		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
    	    userRepo.save(user);
        return "register_success";
    	}
    }

	/*
	 * @GetMapping("/users") public String listUsers(Model model) { List<Users>
	 * listUsers = userRepo.findAll(); model.addAttribute("listUsers", listUsers);
	 * 
	 * return "users"; }
	 */
    
    }


