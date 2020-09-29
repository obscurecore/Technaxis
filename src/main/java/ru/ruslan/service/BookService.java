package ru.ruslan.service;

import ru.ruslan.model.Book;

public interface BookService {
    Book modification(Book newBook, Long id);
    Book saveBook (Book newBook);
    Book findOne (Long id);
    Book updateField(Long id);
}

