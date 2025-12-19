package com.example.BookInventory.service;

import com.example.BookInventory.model.Book;
import com.example.BookInventory.repository.BookRepositoryImpl;
import com.example.BookInventory.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    private final BookRepositoryImpl bookRepository;

    public BookServiceImpl(BookRepositoryImpl bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        if(!bookRepository.existsByIsbn(book.getIsbn())) {
            boolean checkArgs =(( book.getAuthor()!= null) && (!book.getAuthor().isEmpty())&&
                    ( book.getTitle()!= null) && ( !book.getTitle().isEmpty())&&
                    ( book.getIsbn()!= null) && (book.getIsbn().length() == 17));

            if(!checkArgs)
            {
                throw new IllegalArgumentException("Invalid Argument");
            }
            return bookRepository.save(book);
        }else{
            throw new IllegalArgumentException("Book already present with given ISBN.");
        }
    }

    @Override
    public Book getBookById(Long id)  {
        Book book = bookRepository.findById(id);
        if( book != null){
            return book;
        }
        else {
            throw new BookNotFoundException("Book not found with ID: " + id);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public boolean deleteBook(Long id) {
        boolean checkDelete = bookRepository.deleteById(id);
        if(!checkDelete) {
            throw new BookNotFoundException("Book not Found with Id: " + id);
        }
        else
            return true;
    }
}
