package com.example.BookInventory.service;

import com.example.BookInventory.exception.BookNotFoundException;
import com.example.BookInventory.model.Book;
import com.example.BookInventory.repository.BookRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepositoryImpl bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book validBook;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);

        validBook = new Book(
                1L,
                "Clean Code",
                "Robert C. Martin",
                "978-02-01616-22-4", // length = 17
                42.99,
                LocalDateTime.now()
        );
    }

    // ================= ADD BOOK =================

    @Test
    @DisplayName("addBook: should save book when ISBN is unique and arguments are valid")
    void addBook_success() {
        when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(false);
        when(bookRepository.save(validBook)).thenReturn(validBook);

        Book result = bookService.addBook(validBook);

        assertNotNull(result);
        assertEquals(validBook.getId(), result.getId());
        verify(bookRepository, times(1)).save(validBook);
    }

    @Test
    @DisplayName("addBook: should throw exception when ISBN already exists")
    void addBook_duplicateIsbn() {
        when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.addBook(validBook)
        );

        assertEquals("Book already present with given ISBN.", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("addBook: should throw exception when title is empty")
    void addBook_invalidTitle() {
        validBook.setTitle("");

        when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.addBook(validBook)
        );

        assertEquals("Invalid Argument", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("addBook: should throw exception when author is null")
    void addBook_invalidAuthor() {
        validBook.setAuthor(null);

        when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.addBook(validBook)
        );

        assertEquals("Invalid Argument", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("addBook: should throw exception when ISBN length is invalid")
    void addBook_invalidIsbnLength() {
        validBook.setIsbn("12345");

        when(bookRepository.existsByIsbn(validBook.getIsbn())).thenReturn(false);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> bookService.addBook(validBook)
        );

        assertEquals("Invalid Argument", exception.getMessage());
        verify(bookRepository, never()).save(any());
    }

    // ================= GET BOOK BY ID =================

    @Test
    @DisplayName("getBookById: should return book when ID exists")
    void getBookById_success() {
        when(bookRepository.findById(1L)).thenReturn(validBook);

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getBookById: should throw exception when book not found")
    void getBookById_notFound() {
        when(bookRepository.findById(1L)).thenReturn(null);

        BookNotFoundException exception = assertThrows(
                BookNotFoundException.class,
                () -> bookService.getBookById(1L)
        );

        assertEquals("Book not found with ID: 1", exception.getMessage());
    }

    // ================= GET ALL BOOKS =================

    @Test
    @DisplayName("getAllBooks: should return list of books")
    void getAllBooks_success() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(validBook));

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    // ================= DELETE BOOK =================

    @Test
    @DisplayName("deleteBook: should return true when deletion is successful")
    void deleteBook_success() {
        when(bookRepository.deleteById(1L)).thenReturn(true);

        boolean result = bookService.deleteBook(1L);

        assertTrue(result);
        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("deleteBook: should throw exception when book does not exist")
    void deleteBook_notFound() {
        when(bookRepository.deleteById(1L)).thenReturn(false);

        BookNotFoundException exception = assertThrows(
                BookNotFoundException.class,
                () -> bookService.deleteBook(1L)
        );

        assertEquals("Book not Found with Id: 1", exception.getMessage());
    }
}
