package ru.ruslan.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.ruslan.error.BookNotFoundException;
import ru.ruslan.error.BookUnSupportedFieldPatchException;
import ru.ruslan.model.Book;
import ru.ruslan.repository.BookRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isPresent()) {
            return bookOptional.get();
        }
        throw new BookNotFoundException(id);
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

    @Override
    public List<Book> pageableFinding(Integer size) {
        Pageable page = PageRequest.of(0, size, Sort.by("id").descending());
        return repository.findAll(page);
    }

    @Override
    public List<Book> findBooksByKeyword(String s, Integer size) {
        Pageable page = PageRequest.of(0, size);
        return repository.findPhrasesInBooksWithPagination(s, page);

    }


}
