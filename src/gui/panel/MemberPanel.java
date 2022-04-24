package gui.panel;

import library.Book;
import library.Member;
import library.persistence.BookDao;
import library.persistence.DaoFactory;
import library.persistence.MemberDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class MemberPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"ID", "Name"};
    private MemberDao dao;

    public MemberPanel() throws SQLException {
        dao = (MemberDao) new DaoFactory().getDao(DaoFactory.DaoType.MEMBER_DAO);

        initTable();

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initTable() throws SQLException {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columnNames);

        for(Member m: dao.getAll()) {
            dtm.addRow(new Object[] {
                    m.getId(),
                    m.getName(),
            });
        }

        table = new JTable(dtm);

        scrollPane = new JScrollPane(table);
    }
}
