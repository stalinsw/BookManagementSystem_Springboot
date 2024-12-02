package com.book.manage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.manage.models.Book;

public interface BookRepo extends JpaRepository<Book,Long>{

}
