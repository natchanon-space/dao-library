package library;

import javax.persistence.*;

@Entity
@Table(name = "borrow")
public class Borrow {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private String status;

    public Borrow() {
        // ORMLite needs a no-args constructor
    }

    public Borrow(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.status = "B";
    }

    public Borrow(int id, Member member, Book book, String status) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
