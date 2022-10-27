package rs.libgen.zorro.model;

import lombok.Data;

@Data
public class Book {
    private String id;
    private String author;
    private String title;
    private String publisher;
    private String pages;
    private String language;
    private String year;
    private String size;
    private String extension;

    private String mirror1;
    private String mirror2;
}
