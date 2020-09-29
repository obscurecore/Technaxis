package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.model.Book;
import ru.ruslan.repository.BookRepository;
import ru.ruslan.service.BookService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookRestController {
    private final BookRepository repository;
    private final BookService bookService;

    // Find
    @GetMapping("/page/{size}")
    List<Book> findAll(@PathVariable Integer size
            /*  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 10) Pageable pageable*/) {
        Pageable firstPageWithTwoElements = PageRequest.of(0, size);

        return null;// return (List<Book>) repository.findAll(pageable);
    }

    /**
     * Save the book
     *
     * @param newBook the new book
     * @return return 201 status code (that one or more new resources being created)
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Book newBook(@RequestBody Book newBook) {
        return bookService.saveBook(newBook);
    }

    // Find one
    @GetMapping("/{id}")
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
    @PutMapping("/{id}")
    Book editBook(@RequestBody Book newBook, @PathVariable Long id) {
        return bookService.modification(newBook, id);
    }

    // update author only
    @PatchMapping("/{id}")
    Book patch(@PathVariable Long id) {
        return bookService.updateField(id);
    }
}
