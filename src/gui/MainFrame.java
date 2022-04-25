package gui;

import gui.panel.BookPanel;
import gui.panel.BorrowPanel;
import gui.panel.MemberPanel;
import gui.panel.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    JPanel bookPanel;
    JPanel memberPanel;
    JPanel searchPanel;
    JPanel borrowPanel;

    public MainFrame() throws HeadlessException, SQLException {

        JPanel panel = new JPanel();
        panel.add(new Label("Welcome to DAO Library"));
        add(panel);

        bookPanel = new BookPanel();
        memberPanel = new MemberPanel();
        searchPanel = new SearchPanel();
        borrowPanel = new BorrowPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        pack();
        setTitle("DAO Library Management");
        setLayout(new BorderLayout());
    }

    private class MenuAction implements ActionListener {
        private JPanel panel;

        public MenuAction(JPanel panel) {
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            changePanel(panel);
        }
    }

    private void initMenu() throws SQLException {
        JMenuBar menuBar = new JMenuBar();
        JMenu manageMenu = new JMenu("Manage");
        menuBar.add(manageMenu);
        // book
        JMenuItem book = new JMenuItem("Book");
        book.addActionListener(new MenuAction(bookPanel));
        manageMenu.add(book);
        // member
        JMenuItem member = new JMenuItem("Member");
        member.addActionListener(new MenuAction(memberPanel));
        manageMenu.add(member);

        JMenu borrowMenu = new JMenu("Borrow");
        menuBar.add(borrowMenu);
        // search and check status
        JMenuItem checkBorrow = new JMenuItem("Status");
        checkBorrow.addActionListener(new MenuAction(searchPanel));
        borrowMenu.add(checkBorrow);
        // borrow and return service
        JMenuItem bookBorrow = new JMenuItem("Borrow/Return");
        bookBorrow.addActionListener(new MenuAction(borrowPanel));
        borrowMenu.add(bookBorrow);

        setJMenuBar(menuBar);
    }

    private void changePanel(JPanel panel) {
        getContentPane().removeAll();
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().doLayout();
        pack();
        update(getGraphics());
    }

    public static void main(String[] args) throws SQLException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
