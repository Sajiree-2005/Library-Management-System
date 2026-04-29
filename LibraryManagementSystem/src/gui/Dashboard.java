package gui;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Library Dashboard");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4,1,15,15));
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));

        JButton addMember = new JButton("Add Member");
        JButton issueBook = new JButton("Issue Book");
        JButton returnBook = new JButton("Return Book");
        JButton exit = new JButton("Exit");

        panel.add(addMember);
        panel.add(issueBook);
        panel.add(returnBook);
        panel.add(exit);

        add(panel);

        addMember.addActionListener(e -> new AddMemberPage());
        issueBook.addActionListener(e -> new IssueBookPage());
        returnBook.addActionListener(e -> new ReturnBookPage());
        exit.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
}