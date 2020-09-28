package ru.ruslan.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] image;
}