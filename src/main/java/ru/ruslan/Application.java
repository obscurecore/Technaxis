package ru.ruslan;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.ruslan.model.Book;
import ru.ruslan.repository.BookRepository;
import java.net.URL;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            for (int i = 0; i < 100; i++) {

                repository.save(Book.builder().title("title" + i)
                        .description(StringUtils.repeat("Description ", i))
                        .author("author" + i)
                        .isbn("" + i)
                        .printYear(2020 + i)
                        .readAlready(false)
                        .image(new URL("http://example.com/")).build());
            }
        };
    }
}