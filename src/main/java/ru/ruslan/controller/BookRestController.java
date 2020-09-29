package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.model.Book;
import ru.ruslan.repository.BookRepository;
import ru.ruslan.service.BookService;

import java.util.List;

@AllArgsConstructor
public class BookRestController {
    private final BookRepository repository;
    private final BookService bookService;

    // Find
    @GetMapping("/books")
    List<Book> findAll() {
        return repository.findAll();
    }

    /**
     * Save the book
     *
     * @param newBook the new book
     * @return return 201 status code (that one or more new resources being created)
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    Book newBook(@RequestBody Book newBook) {
        return bookService.saveBook(newBook);
    }

    // Find one
    @GetMapping("/books/{id}")
    Book findOne(@PathVariable Long id) {
        return bookService.findOne(id);
    }


    /**
     * Update the book.
     *
     * @param newBook the new book
     * @param id      the id
     * @return the book
     */
    @PutMapping("/books/{id}")
    Book editBook(@RequestBody Book newBook, @PathVariable Long id) {
        return bookService.modification(newBook, id);
    }

    // update author only
    @PatchMapping("/books/{id}")
    Book patch(@PathVariable Long id) {
        return bookService.updateField(id);
    }
}
