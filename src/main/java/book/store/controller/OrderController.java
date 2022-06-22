package book.store.controller;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import book.store.cart.CartRole;
import book.store.model.Book;
import book.store.service.BookService;


@Controller
public class OrderController {

		@Autowired
		private BookService bookService;
		
		
		@GetMapping("/payment")
		public String toPay () {
		return "payment";
		}
		@GetMapping("/toPayment")
		public String checkCart(Model model) {
			double total =CartRole.cart.stream().mapToDouble(Book::getbPrice).sum();
			double discount = CartRole.cart.stream().mapToDouble(Book::getbDiscount).sum();
			
			
			model.addAttribute("discountamt",total - discount );
		
		Boolean cart = CartRole.cart.isEmpty();
		if(cart == true) {
			return "cart";
		}
		else return "payment";
	
		}
		@GetMapping("/orderPlaced")
		public String orderPlaced(Model model) {
			model.addAttribute("book",CartRole.cart);
			double total =CartRole.cart.stream().mapToDouble(Book::getbPrice).sum();
			double discount = CartRole.cart.stream().mapToDouble(Book::getbDiscount).sum();
			
			
			model.addAttribute("discountamt",total - discount );
			return "order";
		}
		@GetMapping("/tohome")
		public String tohome() {
			CartRole.cart.clear();
			return "user_landing";
		}
	
		
	}
