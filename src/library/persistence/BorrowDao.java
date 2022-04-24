package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Book;
import library.Borrow;
import library.Member;

import java.sql.SQLException;
import java.util.List;

public class BorrowDao extends CustomDao<Borrow> {

    public BorrowDao(Dao<Borrow, Integer> dao) {
        super(dao);
    }

    public List<Borrow> getFromMemberId(Member member) throws SQLException {
        return dao.queryBuilder()
                .selectColumns()
                .where()
                .eq("member_id", member.getId())
                .query();
    }

    public List<Borrow> getFromBookId(Book book) throws SQLException {
        return dao.queryBuilder()
                .selectColumns()
                .where()
                .eq("book_id", book.getId())
                .query();
    }
}
