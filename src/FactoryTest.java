import library.Book;
import library.persistence.BookDao;
import library.persistence.DaoFactory;

import java.sql.SQLException;

public class FactoryTest {

    public static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) throws SQLException {
        DaoFactory daoFactory = new DaoFactory(DB_URL);
        BookDao bookDao = (BookDao) daoFactory.getDao(DaoFactory.DaoType.BOOK_DAO);

        Book book = bookDao.get(28);

        System.out.println(book.getId());
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());
    }
}
