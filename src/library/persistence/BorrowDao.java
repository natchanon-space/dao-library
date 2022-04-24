package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Borrow;

import java.sql.SQLException;
import java.util.List;

public class BorrowDao {

    private Dao<Borrow, Integer> dao;

    public BorrowDao(Dao<Borrow, Integer> dao) {
        this.dao = dao;
    }

    public Borrow getBorrow(int id) throws SQLException {
        return dao.queryForId(id);
    }

    public List<Borrow> getAllBorrow() throws SQLException {
        return dao.queryForAll();
    }

    public void createBorrow(Borrow borrow) throws SQLException {
        dao.create(borrow);
    }

    public void updateBorrow(Borrow borrow) throws SQLException {
        dao.update(borrow);
    }

    public void deleteBorrow(Borrow borrow) throws SQLException {
        dao.delete(borrow);
    }
}
