package com.book.manage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.book.manage.models.Book;
import com.book.manage.repositories.BookRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo br;

	public List<Book> getAllBooks() {       //gets all the books
		return br.findAll();
	}

	public Book addBook(Book b) {         //add one new book
		return br.save(b);
	}
	public Book updateBook(Long id, Book b) {       //update one book 

		Optional<Book> book = br.findById(id);
		if (book.isPresent()) {
			Book updatedBook = book.get();
			updatedBook.setBookName(b.getBookName());
			updatedBook.setAuthor(b.getAuthor());
			updatedBook.setGenre(b.getGenre());
			updatedBook.setYear(b.getYear());
			updatedBook.setImageUrl(b.getImageUrl());
			return br.save(updatedBook);
		}
		return null;
	}
	public Optional<Book> getBookById(Long id) {    //gets a book by id
		return br.findById(id);
	}

	public void deleteBook(Long id) {     //deletes a book by id
		br.deleteById(id);
	}

	public Object getUserDetails() {
		return null;
	}
}
