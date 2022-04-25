package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Book;
import library.Borrow;
import library.Member;

import java.sql.SQLException;
import java.util.List;

public class BorrowDao extends CustomDao<Borrow> {

    private BookDao bookDao;
    private MemberDao memberDao;

    public BorrowDao(Dao<Borrow, Integer> dao, BookDao bookDao, MemberDao memberDao) {
        super(dao);
        this.bookDao = bookDao;
        this.memberDao = memberDao;
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

    public void eagerRefresh(Borrow borrow) throws SQLException {
        Book book = bookDao.get(borrow.getBook().getId());
        Member member = memberDao.get(borrow.getMember().getId());

        borrow.setBook(book);
        borrow.setMember(member);
    }
}
