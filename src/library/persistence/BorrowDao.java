package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Borrow;

public class BorrowDao extends CustomDao<Borrow> {

    public BorrowDao(Dao<Borrow, Integer> dao) {
        super(dao);
    }
}
