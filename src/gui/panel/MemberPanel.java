package gui.panel;

import com.j256.ormlite.stmt.query.In;
import library.Book;
import library.Member;
import library.persistence.BookDao;
import library.persistence.DaoFactory;
import library.persistence.MemberDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                try {
                    dao.create(new Member(name));
                    remove(scrollPane);
                    initTable();
                    add(scrollPane);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                nameField.setText("");
                revalidate();
            }
        });
    }

    private void initDeleteForm() {
        deleteFormPanel = new JPanel();

        JTextField idField = new JTextField(5);
        JButton deleteButton = new JButton("delete");

        deleteFormPanel.add(new JLabel("[Delete]"));
        deleteFormPanel.add(new JLabel("id: "));
        deleteFormPanel.add(idField);
        deleteFormPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());

                try {
                    Member member = dao.get(id);
                    dao.delete(member);
                    remove(scrollPane);
                    initTable();
                    add(scrollPane);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                idField.setText("");
                revalidate();
            }
        });
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

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();

                try {
                    Member member = dao.get(id);
                    if(!nameField.getText().isBlank()) {
                        member.setName(name);
                    }
                    dao.update(member);

                    remove(scrollPane);
                    initTable();
                    add(scrollPane);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                idField.setText("");
                nameField.setText("");
                revalidate();
            }
        });
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
