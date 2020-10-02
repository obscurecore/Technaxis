package ru.ruslan.service;

import ru.ruslan.dto.BookStatistics;
import ru.ruslan.model.Book;

import java.sql.Date;
import java.util.List;

public interface BookService {
    Book modification(Book newBook, Long id);
    Book saveBook (Book newBook);
    Book findOne (Long id);
    Book updateField(Long id);
    List<Book> pageableFinding(Integer size);
    List<Book> findBooksByKeyword (String s, Integer size);
    List<BookStatistics> bookStatistics (Date d1,Date d2);

}

