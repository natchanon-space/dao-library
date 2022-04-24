package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Book;

public class BookDao extends CustomDao<Book> {

    public BookDao(Dao<Book, Integer> dao) {
        super(dao);
    }
}
