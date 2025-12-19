package com.example.BookInventory.repository;

import com.example.BookInventory.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final Map<Long, Book> bookStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Book save(Book book) {
        book.setId(idGenerator.getAndIncrement());
        bookStore.put(book.getId(), book);
        return book;
    }

    @Override
    public Book findById(Long id) {
        return bookStore.getOrDefault(id,null);
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(bookStore.values());
    }

    @Override
    public boolean deleteById(Long id) {
        return( bookStore.remove(id) != null);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookStore.values().stream()
                .anyMatch(book -> book.getIsbn().equals(isbn));
    }

}
