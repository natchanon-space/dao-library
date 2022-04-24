package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDao {

    private Dao<Book, Integer> dao;

    public BookDao(Dao<Book, Integer> dao) {
        this.dao = dao;
    }

    public Book getBook(int id) throws SQLException {
        return dao.queryForId(id);
    }

    public List<Book> getAllBook() throws SQLException {
        return dao.queryForAll();
    }

    public void createBook(Book book) throws SQLException {
        dao.create(book);
    }

    public void updateBook(Book book) throws SQLException {
        dao.update(book);
    }

    public void deleteBook(Book book) throws SQLException {
        dao.delete(book);
    }
}
