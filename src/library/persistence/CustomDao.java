package library.persistence;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public abstract class CustomDao<T> {

    protected Dao<T, Integer> dao;

    public CustomDao(Dao<T, Integer> dao) {
        this.dao = dao;
    }

    public T get(int id) throws SQLException {
        return dao.queryForId(id);
    }

    public List<T> getAll() throws SQLException {
        return dao.queryForAll();
    }

    public void create(T newRecord) throws SQLException {
        dao.create(newRecord);
    }

    public void update(T updateRecord) throws SQLException {
        dao.update(updateRecord);
    }

    public void delete(T deleteRecord) throws SQLException {
        dao.delete(deleteRecord);
    }
}
