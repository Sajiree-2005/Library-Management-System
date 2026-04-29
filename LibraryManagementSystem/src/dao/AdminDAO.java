package dao;

import db.DBConnection;
import java.sql.*;

public class AdminDAO {

    public boolean login(String username, String password) throws Exception {

        if (username.isEmpty() || password.isEmpty())
            throw new Exception("Invalid input");

        String sql = "SELECT * FROM Admin WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}