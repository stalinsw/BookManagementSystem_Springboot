package com.book.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.manage.models.Book;
import com.book.manage.models.User;
import com.book.manage.repositories.UserRepo;
import com.book.manage.services.BookService;

import jakarta.validation.Valid;

@Controller
public class BookController {

	@Autowired
	private BookService br;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepo userRepository;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("books", br.getAllBooks()); // Lists all the books
		return "index.html";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {  //to create a register form
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "register";
		}
		user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
		userRepository.save(user);
		return "redirect:/login";
	}

	@GetMapping("/book/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Book book = br.getBookById(id).orElse(null); // Selects the book which we want to update and shows the edit form
		model.addAttribute("book", book);
		return "edit-book.html";
	}

	@GetMapping("/book/{id}")
	public String getBook(@PathVariable Long id, Model model) {
		model.addAttribute("book", br.getBookById(id).orElse(null)); // get the book details
		return "book-details";
	}

	@GetMapping("/book/new")
	public String showAddForm(Model model) { // get the form to add the book
		model.addAttribute("book", new Book());
		return "add-book";
	}

	@PostMapping("/book/new/added")
	public String saveBook(Book book) { // to post or add a new book
		br.addBook(book);
		return "redirect:/";
	}

	@PostMapping("/update/{id}")
	public String UpdateTask(@ModelAttribute Book book, @PathVariable Long id) { // Edit a book
		br.updateBook(id, book);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteTask(@PathVariable Long id) { // deletes a book
		br.deleteBook(id);
		return "redirect:/";
	}
}
