package library.persistence;

import com.j256.ormlite.dao.Dao;

public class BorrowDao extends CustomDao<BorrowDao> {

    public BorrowDao(Dao<BorrowDao, Integer> dao) {
        super(dao);
    }
}
