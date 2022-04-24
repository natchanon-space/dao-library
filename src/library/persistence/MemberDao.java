package library.persistence;

import com.j256.ormlite.dao.Dao;
import library.Member;

public class MemberDao extends CustomDao<Member> {

    public MemberDao(Dao<Member, Integer> dao) {
        super(dao);
    }
}
