package gui.panel;

import library.Borrow;
import library.persistence.BookDao;
import library.persistence.BorrowDao;
import library.persistence.DaoFactory;
import library.persistence.MemberDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchPanel extends JPanel {

    private BorrowDao dao;
    private BookDao bookDao;
    private MemberDao memberDao;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"ID", "Book ID", "Title", "Name", "Status"};

    private JPanel formPanel;

    public SearchPanel() throws SQLException {
        this.dao = (BorrowDao) new DaoFactory().getDao(DaoFactory.DaoType.BORROW_DAO);
        this.bookDao = (BookDao) new DaoFactory().getDao(DaoFactory.DaoType.BOOK_DAO);
        this.memberDao = (MemberDao) new DaoFactory().getDao(DaoFactory.DaoType.MEMBER_DAO);

        initTable(null);
        initForm();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(formPanel);
        add(scrollPane);
    }

    private void initForm() {
        formPanel = new JPanel();

        JButton submitButton = new JButton("Submit");
        JTextField searchField = new JTextField(5);

        JComboBox filter = new JComboBox();
        filter.addItem("book id");
        filter.addItem("member id");

        formPanel.add(new JLabel("[Search]"));
        formPanel.add(filter);
        formPanel.add(searchField);
        formPanel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filterOption = filter.getSelectedIndex();
                int id = Integer.parseInt(searchField.getText());

                List<Borrow> borrowList = null;
                try {
                    switch (filterOption) {
                        case 0:
                            borrowList = dao.getFromBookId(bookDao.get(id));
                            break;
                        case 1:
                            borrowList = dao.getFromMemberId(memberDao.get(id));
                            break;
                    }

                    for(Borrow b: borrowList) {
                        dao.eagerRefresh(b);
                    }

                    remove(scrollPane);
                    initTable(borrowList);
                    add(scrollPane);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                revalidate();
            }
        });
    }

    private void initTable(List<Borrow> borrowList) {
        DefaultTableModel dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(columnNames);

        if (borrowList != null) {
            for (Borrow b : borrowList) {
                dtm.addRow(new Object[]{
                        b.getId(),
                        b.getBook().getId(),
                        b.getBook().getTitle(),
                        b.getMember().getName(),
                        b.getStatus()
                });
            }
        }

        table = new JTable(dtm);

        scrollPane = new JScrollPane(table);
    }
}
