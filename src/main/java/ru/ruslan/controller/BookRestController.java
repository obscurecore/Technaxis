package ru.ruslan.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.dto.BookStatistics;
import ru.ruslan.model.Book;
import ru.ruslan.service.contract.BookService;
import ru.ruslan.service.contract.ConstraintService;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Min;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/books")
@Validated
public class BookRestController {
    private final BookService bookService;
    private final ConstraintService constraintService;

    /**
     * Get a list of books
     *
     * @param size length of the page. Minimum is 1
     * @return the list
     */
    @GetMapping("/page/{size}")
    List<Book> pageableFinding(@Min(1) @PathVariable(required = true) Integer size) {
        return bookService.pageableFinding(size);
    }

    /**
     * Book statistics list. Doesn't matter what order
     *
     * @param date1 one border
     * @param date2 one border
     * @return the list
     */
    @GetMapping("/statistics/{date1}/{date2}")
    List<BookStatistics> bookStatistics(@PathVariable(required = true) Date date1, @PathVariable(required = true) Date date2) {
        return bookService.bookStatistics(date1, date2);
    }


    /**
     * Find books by keyword and paginated output.
     *
     * @param s    the phrase
     * @param size the size of page
     * @return the list
     */
    @GetMapping("/phrase/{s}/page/{size}")
    List<Book> findBooksByKeyword(@PathVariable(required = true) String s, @PathVariable(required = true) Integer size) {
        return bookService.findBooksByKeyword(s, size);
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


    /**
     * Find one book.
     *
     * @param id the id
     * @return the book
     */
    @GetMapping("/{id}")
    Book findOne(@PathVariable Long id) {
        return bookService.findOne(id);
    }


    /**
     * Update the book.
     *
     * @param newBook the new book
     * @param id      the id
     * @return 200 code
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void editBook(@RequestBody Book newBook, @PathVariable Long id) {
        bookService.modification(newBook, id);
    }

    /**
     * Update status of reading the book - readAlready.
     *
     * @param id the id
     * @return 200 code
     */
    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    void patch(@PathVariable Long id) {
        bookService.updateField(id);
    }

    /**
     * Handle constraint validation exceptions
     *
     * @param ex the ex
     * @return the map
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleConstraintValidationExceptions(ConstraintViolationException ex) {
        return constraintService.getConstraintErrors(ex);
    }
}
