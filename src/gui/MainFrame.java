package gui;

import gui.panel.BookPanel;
import gui.panel.MemberPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrame extends JFrame {

    JPanel bookPanel;
    JPanel memberPanel;

    public MainFrame() throws HeadlessException, SQLException {

        JPanel panel = new JPanel();
        panel.add(new Label("Welcome to DAO Library"));
        add(panel);

        bookPanel = new BookPanel();
        memberPanel = new MemberPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initMenu();

        pack();
        setSize(new Dimension(600, 800));
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
        // TODO: add panel and action event listener
        // borrow and return service
        JMenuItem bookBorrow = new JMenuItem("Borrow/Return");
        // TODO: add panel and action event listener

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
