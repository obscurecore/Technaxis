package ru.ruslan.repository.contract;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.ruslan.model.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll(Pageable page);

    @Query(value = "SELECT book.id,author,description,image,isbn, print_year,read_already, title FROM book INNER JOIN book_vector bv on book.id = bv.id\n" +
            "WHERE tsv @@ plainto_tsquery(:phrase) ORDER BY id",
            countQuery = "SELECT count(*) FROM book",
            nativeQuery = true)
    List<Book> findPhrasesInBooksWithPagination(@Param("phrase") String phrase, Pageable pageable);
}