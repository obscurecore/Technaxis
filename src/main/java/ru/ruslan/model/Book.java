package ru.ruslan.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Size(max = 100)
    private String title;

    @Lob
    private String description;

    @Size(max = 100)
    private String author;

    @Size(max = 20)
    private String isbn;

    private Integer printYear;
    private boolean readAlready;
    private URL image;

}
