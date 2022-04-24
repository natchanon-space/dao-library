import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import library.Book;
import library.Borrow;
import library.Member;

import java.sql.SQLException;

public class OrmTest {

    public static final String DB_URL = "jdbc:sqlite:library.db";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectionSource connectionSource = new JdbcConnectionSource(DB_URL);

        Dao<Borrow, Integer> borrowDao = DaoManager.createDao(connectionSource, Borrow.class);

        Borrow borrow1 = borrowDao.queryForId(50);

        Book book = borrow1.getBook();
        Member member = borrow1.getMember();

        System.out.printf("Book ID: %d\n", book.getId());
        System.out.printf("Book's Author: %s\n", book.getTitle());
        System.out.printf("Member ID: %d\n", member.getId());
        System.out.printf("Member's Name: %s\n", member.getName());
    }
}
