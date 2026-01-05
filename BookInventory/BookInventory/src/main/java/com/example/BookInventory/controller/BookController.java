package com.example.BookInventory.controller;

import com.example.BookInventory.model.Book;
import com.example.BookInventory.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    private final BookService bookService;

    // Constructor injection
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/v1/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/api/v1/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/api/v1/books")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book added successfully");
        response.put("book id:", savedBook.getId().toString());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/books/{id}")
    public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book deleted successfully");
        response.put("deletedBookId", id.toString());

        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/api/v1/books/author/{Author}")
    public ResponseEntity<List<Book>> getBookByAuthorName(@PathVariable String Author){
        List<Book> booksWithGivenAuthor = bookService.getBooksByAuthorName(Author);
        return new ResponseEntity<>(booksWithGivenAuthor, HttpStatus.OK);
    }
}
