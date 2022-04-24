package gui.panel;

import library.Book;
import library.persistence.BookDao;
import library.persistence.DaoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class BookPanel extends JPanel {

    private BookDao dao;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"ID", "Title", "Author"};

    private JPanel addFormPanel;
    private JPanel deleteFormPanel;
    private JPanel updateFormPanel;

    public BookPanel() throws SQLException {
        dao = (BookDao) new DaoFactory().getDao(DaoFactory.DaoType.BOOK_DAO);

        initTable();
        initAddForm();
        initDeleteForm();
        initUpdateForm();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(addFormPanel);
        add(deleteFormPanel);
        add(updateFormPanel);
        add(scrollPane);
    }

    private void initAddForm() {
        addFormPanel = new JPanel();

        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JButton addButton = new JButton("add");

        addFormPanel.add(new JLabel("[Create]"));
        addFormPanel.add(new JLabel("title: "));
        addFormPanel.add(titleField);
        addFormPanel.add(new JLabel("author: "));
        addFormPanel.add(authorField);
        addFormPanel.add(addButton);
    }

    private void initDeleteForm() {
        deleteFormPanel = new JPanel();

        JTextField idField = new JTextField(5);
        JButton deleteButton = new JButton("delete");

        deleteFormPanel.add(new JLabel("[Delete]"));
        deleteFormPanel.add(new JLabel("id: "));
        deleteFormPanel.add(idField);
        deleteFormPanel.add(deleteButton);
    }

    private void initUpdateForm() {
        updateFormPanel = new JPanel();

        JTextField idField = new JTextField(5);
        JTextField titleField = new JTextField(20);
        JTextField authorField = new JTextField(20);
        JButton updateButton = new JButton("Update");

        updateFormPanel.add(new JLabel("[Update]"));
        updateFormPanel.add(new JLabel("id: "));
        updateFormPanel.add(idField);
        updateFormPanel.add(new JLabel("title: "));
        updateFormPanel.add(titleField);
        updateFormPanel.add(new JLabel("author: "));
        updateFormPanel.add(authorField);
        updateFormPanel.add(updateButton);
    }

    private void initTable() throws SQLException {
        DefaultTableModel dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
