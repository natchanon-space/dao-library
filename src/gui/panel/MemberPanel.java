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

    private JPanel addFormPanel;
    private JPanel deleteFormPanel;
    private JPanel updateFormPanel;

    public MemberPanel() throws SQLException {
        dao = (MemberDao) new DaoFactory().getDao(DaoFactory.DaoType.MEMBER_DAO);

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

        JTextField nameField = new JTextField(20);
        JButton addButton = new JButton("add");

        addFormPanel.add(new JLabel("[Create]"));
        addFormPanel.add(new JLabel("name: "));
        addFormPanel.add(nameField);
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
        JTextField nameField = new JTextField(20);
        JButton updateButton = new JButton("Update");

        updateFormPanel.add(new JLabel("[Update]"));
        updateFormPanel.add(new JLabel("id: "));
        updateFormPanel.add(idField);
        updateFormPanel.add(new JLabel("name: "));
        updateFormPanel.add(nameField);
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
