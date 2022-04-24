package gui.panel;

import library.persistence.BorrowDao;
import library.persistence.DaoFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SearchPanel extends JPanel {

    private BorrowDao dao;

    private JTable table;
    private JScrollPane scrollPane;
    private String[] columnNames = {"Title", "Name", "Status"};

    private JPanel formPanel;

    public SearchPanel() throws SQLException {
        this.dao = (BorrowDao) new DaoFactory().getDao(DaoFactory.DaoType.BORROW_DAO);

        initTable();
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
                // TODO: implement table and DAO search
                System.out.println(filter.getSelectedIndex());
                System.out.println(searchField.getText());
            }
        });
    }

    private void initTable() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(columnNames);

        // TODO: add table data here

        table = new JTable(dtm);

        scrollPane = new JScrollPane(table);
    }
}
