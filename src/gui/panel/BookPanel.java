package gui.panel;

import library.Book;
import library.persistence.BookDao;
import library.persistence.DaoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class BookPanel extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"ID", "Title", "Author"};
    private BookDao dao;

    public BookPanel() throws SQLException {
        dao = (BookDao) new DaoFactory().getDao(DaoFactory.DaoType.BOOK_DAO);

        initTable();

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void initTable() throws SQLException {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        dtm.setColumnIdentifiers(columnNames);

        for(Book b: dao.getAll()) {
            dtm.addRow(new Object[] {
                    b.getId(),
                    b.getTitle(),
                    b.getAuthor()
            });
        }

        table = new JTable(dtm);

        scrollPane = new JScrollPane(table);
    }
}
