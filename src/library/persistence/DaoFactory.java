package library.persistence;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import library.Book;
import library.Borrow;
import library.Member;

import java.sql.SQLException;

public class DaoFactory {

    public enum DaoType {
        BOOK_DAO,
        BORROW_DAO,
        MEMBER_DAO
    }

    private ConnectionSource connectionSource;

    public DaoFactory(String databaseURL) throws SQLException {
        this.connectionSource = new JdbcConnectionSource(databaseURL);
    }

    public DaoFactory(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

    public Object getDao(DaoType daoType) throws SQLException {
        switch (daoType) {
            case BOOK_DAO:
                return new BookDao(DaoManager.createDao(connectionSource, Book.class));
            case BORROW_DAO:
                return new BorrowDao(DaoManager.createDao(connectionSource, Borrow.class));
            case MEMBER_DAO:
                return new MemberDao(DaoManager.createDao(connectionSource, Member.class));
        }
        return null;
    }
}
