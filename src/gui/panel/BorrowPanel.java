package gui.panel;

import library.Book;
import library.Borrow;
import library.Member;
import library.persistence.BookDao;
import library.persistence.BorrowDao;
import library.persistence.DaoFactory;
import library.persistence.MemberDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BorrowPanel extends JPanel {

    private BorrowDao dao;
    private BookDao bookDao;
    private MemberDao memberDao;

    private JLabel titleText;
    private JLabel messageText;

    private JPanel borrowFormPanel;
    private JPanel returnFormPanel;

    public BorrowPanel() throws SQLException {
        this.dao = (BorrowDao) new DaoFactory().getDao(DaoFactory.DaoType.BORROW_DAO);
        this.bookDao = (BookDao) new DaoFactory().getDao(DaoFactory.DaoType.BOOK_DAO);
        this.memberDao = (MemberDao) new DaoFactory().getDao(DaoFactory.DaoType.MEMBER_DAO);

        titleText = new JLabel("Welcome to Borrow/Return Service");
        messageText = new JLabel();

        initBorrowForm();
        initReturnForm();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 100));
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
                Member member;
                Book book;
                Borrow borrow;

                try {
                    member = memberDao.get(Integer.parseInt(memberIdField.getText()));
                    book = bookDao.get(Integer.parseInt(bookIdField.getText()));

                    borrow = new Borrow(member, book);
                    dao.create(borrow);

                    messageText.setText(String.format("Message: '%s' has borrowed '%s'", member.getName(), book.getTitle()));
                } catch (Exception ex) {
                    messageText.setText("Error: Invalid user id or book id");
                    throw new RuntimeException(ex);
                }

                revalidate();
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
                Borrow borrow;

                try {
                    borrow = dao.get(Integer.parseInt(returnIdField.getText()));
                    borrow.setStatus("R");
                    dao.update(borrow);

                    messageText.setText(String.format("Message: Update status borrow id %d to be returned", borrow.getId()));
                } catch (Exception ex) {
                    messageText.setText("Error: Invalid borrow id");
                    throw new RuntimeException(ex);
                }

                revalidate();
            }
        });
    }
}
