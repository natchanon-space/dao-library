import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import library.Book;
import library.Borrow;
import library.Member;
import library.persistence.BookDao;
import library.persistence.MemberDao;

import java.sql.SQLException;

public class OrmTest {

    public static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL);

        BookDao bookDao = new BookDao(DaoManager.createDao(connectionSource, Book.class));

        Book book = bookDao.get(28);

        System.out.println(book.getTitle());
        System.out.println(book.getAuthor());

        MemberDao memberDao = new MemberDao(DaoManager.createDao(connectionSource, Member.class));

        Member member = memberDao.get(27);

        System.out.println(member.getName());
    }
}
