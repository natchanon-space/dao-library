package gui.panel;

import library.persistence.BorrowDao;
import library.persistence.DaoFactory;

import javax.swing.*;
import java.sql.SQLException;

public class BorrowPanel extends JPanel {

    private BorrowDao dao;
    private JLabel titleText;
    private JLabel messageText;

    private JPanel borrowFormPanel;
    private JLabel returnFormPanel;

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

        // TODO: implement borrow form

    }

    public void initReturnForm() {
        returnFormPanel = new JLabel();

        // TODO: implement return form
    }
}
