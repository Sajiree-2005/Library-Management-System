package gui;

import service.LibraryService;

import javax.swing.*;
import java.awt.*;

public class IssueBookPage extends JFrame {

    JTextField memberId, bookId;
    JButton issueBtn;

    LibraryService service = new LibraryService();

    public IssueBookPage() {

        setTitle("Issue Book");
        setSize(400,250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        memberId = new JTextField();
        bookId = new JTextField();
        issueBtn = new JButton("Issue");

        panel.add(new JLabel("Member ID"));
        panel.add(memberId);
        panel.add(new JLabel("Book ID"));
        panel.add(bookId);
        panel.add(issueBtn);

        add(panel);

        issueBtn.addActionListener(e -> issueBook());

        setVisible(true);
    }

    void issueBook() {
        try {
            int mId = Integer.parseInt(memberId.getText());
            int bId = Integer.parseInt(bookId.getText());

            service.issueBook(mId, bId);

            JOptionPane.showMessageDialog(this, "Book Issued");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}