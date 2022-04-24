package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Member;

import java.sql.SQLException;
import java.util.List;

public class MemberDao {

    private Dao<Member, Integer> dao;

    public MemberDao(Dao<Member, Integer> dao) {
        this.dao = dao;
    }

    public Member getMember(int id) throws SQLException {
        return dao.queryForId(id);
    }

    public List<Member> getAllMember() throws SQLException {
        return dao.queryForAll();
    };

    public void createMember(Member member) throws SQLException {
        dao.create(member);
    }

    public void updateMember(Member member) throws SQLException {
        dao.update(member);
    }

    public void deleteMember(Member member) throws SQLException {
        dao.create(member);
    }
}
