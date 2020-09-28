package ru.ruslan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ruslan.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}