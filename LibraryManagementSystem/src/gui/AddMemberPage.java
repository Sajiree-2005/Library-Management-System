package gui;

import service.LibraryService;
import model.Member;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class AddMemberPage extends JFrame {

    JTextField name, email, phone;
    JButton addBtn;

    LibraryService service = new LibraryService();

    public AddMemberPage() {

        setTitle("Add Member");
        setSize(400,300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        name = new JTextField();
        email = new JTextField();
        phone = new JTextField();
        addBtn = new JButton("Add");

        panel.add(new JLabel("Name"));
        panel.add(name);
        panel.add(new JLabel("Email"));
        panel.add(email);
        panel.add(new JLabel("Phone"));
        panel.add(phone);
        panel.add(addBtn);

        add(panel);

        addBtn.addActionListener(e -> addMember());

        setVisible(true);
    }

    void addMember() {
        try {
            Member m = new Member();
            m.name = name.getText();
            m.email = email.getText();
            m.phone = phone.getText();
            m.membershipDate = new Date(System.currentTimeMillis());
            m.status = "ACTIVE";

            service.addMember(m);

            JOptionPane.showMessageDialog(this, "Member Added");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}