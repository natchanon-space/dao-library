package library;

public class Borrow {

    public int id;
    public Member member;
    public Book book;
    public String status;

    public Borrow(int id, Member member, Book book, String status) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.status = status;
    }
}
