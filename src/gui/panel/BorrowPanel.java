package gui.panel;

import library.persistence.BorrowDao;
import library.persistence.DaoFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BorrowPanel extends JPanel {

    private BorrowDao dao;
    private JLabel titleText;
    private JLabel messageText;

    private JPanel borrowFormPanel;
    private JPanel returnFormPanel;

    public BorrowPanel() throws SQLException {
        this.dao = (BorrowDao) new DaoFactory().getDao(DaoFactory.DaoType.BORROW_DAO);

        titleText = new JLabel("Welcome to Borrow/Return Service");
        messageText = new JLabel();

        initBorrowForm();
        initReturnForm();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(titleText);
        add(borrowFormPanel);
        add(returnFormPanel);
        add(messageText);
    }

    public void initBorrowForm() {
        borrowFormPanel = new JPanel();

        JTextField memberIdField = new JTextField(5);
        JTextField bookIdField = new JTextField(5);
        JButton borrowButton = new JButton("Borrow");

        borrowFormPanel.add(new JLabel("[Borrow]"));
        borrowFormPanel.add(new JLabel("member id: "));
        borrowFormPanel.add(memberIdField);
        borrowFormPanel.add(new JLabel("book id: "));
        borrowFormPanel.add(bookIdField);
        borrowFormPanel.add(borrowButton);

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: add event listener
            }
        });
    }

    public void initReturnForm() {
        returnFormPanel = new JPanel();

        JTextField returnIdField = new JTextField(5);
        JButton returnButton = new JButton("Return");

        returnFormPanel.add(new JLabel("[Return]"));
        returnFormPanel.add(new JLabel("borrow id: "));
        returnFormPanel.add(returnIdField);
        returnFormPanel.add(returnButton);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: add event listener
            }
        });
    }
}
