package ru.ruslan.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.ruslan.error.BookNotFoundException;
import ru.ruslan.error.BookUnSupportedFieldPatchException;
import ru.ruslan.model.Book;
import ru.ruslan.repository.BookRepository;

@Component
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public Book modification(Book newBook, Long id) {
        return repository.findById(id)
                .map(x -> {
                    x.setTitle(newBook.getTitle());
                    x.setDescription(newBook.getDescription());
                    x.setIsbn(newBook.getIsbn());
                    x.setPrintYear(newBook.getPrintYear());
                    x.setImage(newBook.getImage());
                    return repository.save(x);
                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });
    }

    @Override
    public Book saveBook(Book newBook) {
        return repository.save(newBook);
    }

    @Override
    public Book findOne(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book updateField(Long id) {
        return repository.findById(id)
                .map(x -> {
                    if (!x.isReadAlready()) {
                        x.setReadAlready(true);
                        return repository.save(x);
                    } else {
                        throw new BookUnSupportedFieldPatchException("ReadAlready");
                    }

                })
                .orElseGet(() -> {
                    throw new BookNotFoundException(id);
                });

    }


}
