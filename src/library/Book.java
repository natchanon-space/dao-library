package library;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    public Book() {
        // ORMLite needs a no-args constructor
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = (author == null) ?  "Unknown" : author;
    }

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = (author == null) ?  "Unknown" : author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
