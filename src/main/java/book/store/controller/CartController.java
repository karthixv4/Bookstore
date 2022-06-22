package book.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import book.store.cart.CartRole;
import book.store.model.Book;
import book.store.service.BookService;



@Controller
public class CartController {

	@Autowired
	private BookService bookService;
	
	
	private Book book;
	
	@GetMapping("/toCart/{id}")
	public String addToCart (@PathVariable Long id) {
	CartRole.cart.add(bookService.showAllBooksById(id).get());
	return"redirect:/cart";
	}
	@GetMapping("/cart")
	public String cart(Model model) {
	model.addAttribute("TotalCount",CartRole.cart.size());
	model.addAttribute("totalamt", CartRole.cart.stream().mapToDouble(Book::getbPrice).sum());
	double total =CartRole.cart.stream().mapToDouble(Book::getbPrice).sum();
	double discount = CartRole.cart.stream().mapToDouble(Book::getbDiscount).sum();
	
	
	model.addAttribute("discountamt",total - discount );
	model.addAttribute("totaldiscount",CartRole.cart.stream().mapToDouble(Book::getbDiscount).sum() );

	model.addAttribute("book",CartRole.cart);

	return"cart";
	}
	@GetMapping("/removeBook/{index}")
	public String cartItemRemove(@PathVariable ("index") int index) {
	CartRole.cart.remove(index);
	return"redirect:/cart";
	}
	
}
