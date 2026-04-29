package dao;

import db.DBConnection;
import model.Member;
import java.sql.*;
import java.util.regex.Pattern;

public class MemberDAO {

    public void addMember(Member m) throws Exception {

        if (m.name == null || m.name.isEmpty())
            throw new Exception("Name required");

        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", m.email))
            throw new Exception("Invalid email");

        if (!m.phone.matches("\\d{10}"))
            throw new Exception("Invalid phone");

        String sql = "INSERT INTO Member(name,email,phone,membership_date,status) VALUES (?,?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.name);
            ps.setString(2, m.email);
            ps.setString(3, m.phone);
            ps.setDate(4, m.membershipDate);
            ps.setString(5, m.status);

            ps.executeUpdate();
        }
    }

    public Member getMemberById(int id) throws Exception {
        String sql = "SELECT * FROM Member WHERE member_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Member m = new Member();
                m.memberId = id;
                m.status = rs.getString("status");
                return m;
            }
        }
        return null;
    }
}