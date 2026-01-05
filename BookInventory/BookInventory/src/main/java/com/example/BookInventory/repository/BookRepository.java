package com.example.BookInventory.repository;

import com.example.BookInventory.model.Book;

import java.util.List;

public interface BookRepository {

    /**
     * Saves a new book to the inventory
     * @param book The book to save
     * @return The saved book with generated ID
     */
    Book save(Book book);

    /**
     * Finds a book by its ID
     * @param id The book ID
     * @return Optional containing the book if found, empty otherwise
     */
    Book findById(Long id);

    /**
     * Retrieves all books in the inventory
     * @return List of all books
     */
    List<Book> findAll();

    /**
     * Deletes a book by its ID
     * @param id The book ID to delete
     * @return true if deleted, false if not found
     */
    boolean deleteById(Long id);

    /**
     * Checks if a book with given ISBN already exists
     * @param isbn The ISBN to check
     * @return true if exists, false otherwise
     */
    boolean existsByIsbn(String isbn);

     List<Book> findByAuthorName(String Author);
}
