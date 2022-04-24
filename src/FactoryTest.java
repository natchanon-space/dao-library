import library.Book;
import library.Borrow;
import library.Member;
import library.persistence.BookDao;
import library.persistence.BorrowDao;
import library.persistence.DaoFactory;
import library.persistence.MemberDao;

import java.sql.SQLException;
import java.util.List;

public class FactoryTest {

    public static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) throws SQLException {
        DaoFactory daoFactory = new DaoFactory(DB_URL);
        BookDao bookDao = (BookDao) daoFactory.getDao(DaoFactory.DaoType.BOOK_DAO);

        Book book = bookDao.get(28);

        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());

        BorrowDao borrowDao = (BorrowDao) daoFactory.getDao(DaoFactory.DaoType.BORROW_DAO);
        MemberDao memberDao = (MemberDao) daoFactory.getDao(DaoFactory.DaoType.MEMBER_DAO);

        List<Borrow> borrows = borrowDao.getFromMemberId(memberDao.get(10));
        for(Borrow borrow: borrows) {
            borrow.setBook(bookDao.get(borrow.getBook().getId()));
            borrow.setMember(memberDao.get(borrow.getMember().getId()));
            System.out.println(borrow.getId());
            System.out.println(borrow.getMember().getName());
            System.out.println(borrow.getBook().getTitle());
            System.out.println(borrow.getStatus());
        }
    }
}
