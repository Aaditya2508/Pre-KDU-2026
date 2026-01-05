package com.example.BookInventory.service;

import com.example.BookInventory.model.Book;

import java.util.List;

public interface BookService {
    Book addBook(Book book);
    Book getBookById(Long id);
    List<Book> getAllBooks();
    boolean deleteBook(Long id);
    List<Book> getBooksByAuthorName(String Author);
}
