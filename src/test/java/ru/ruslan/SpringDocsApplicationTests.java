package ru.ruslan;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.ruslan.model.Book;
import ru.ruslan.repository.contract.BookRepository;

import java.net.URL;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "build/generated-snippets")
public class SpringDocsApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @After
    public void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void testGetBook() throws Exception {
        // Given
        Book book = Book.builder().title("Ruslan")
                .description("Description")
                .author("author")
                .isbn("")
                .printYear(2020)
                .readAlready(false)
                .image(new URL("http://example.com/")).build();
        bookRepository.save(book);

        mockMvc.perform(get("/books/{id}", book.getId()))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("title", is("Ruslan")))
                .andExpect((ResultMatcher) jsonPath("description", is("Something")))

        ;
    }

}