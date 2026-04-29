package gui;

import service.LibraryService;

import javax.swing.*;
import java.awt.*;

public class ReturnBookPage extends JFrame {

    JTextField issueId;
    JButton returnBtn;

    LibraryService service = new LibraryService();

    public ReturnBookPage() {

        setTitle("Return Book");
        setSize(400,200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        issueId = new JTextField();
        returnBtn = new JButton("Return");

        panel.add(new JLabel("Issue ID"));
        panel.add(issueId);
        panel.add(returnBtn);

        add(panel);

        returnBtn.addActionListener(e -> returnBook());

        setVisible(true);
    }

    void returnBook() {
        try {
            int id = Integer.parseInt(issueId.getText());
            service.returnBook(id);

            JOptionPane.showMessageDialog(this, "Book Returned");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}