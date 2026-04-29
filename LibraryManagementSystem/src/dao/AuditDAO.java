package dao;

import db.DBConnection;
import model.Audit;
import java.sql.*;
import java.util.*;

public class AuditDAO {

    public List<Audit> getAuditLogs() throws Exception {
        List<Audit> list = new ArrayList<>();

        String sql = "SELECT * FROM Library_Audit";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Audit a = new Audit();
                a.auditId = rs.getInt("audit_id");
                a.tableName = rs.getString("table_name");
                a.operationType = rs.getString("operation_type");
                list.add(a);
            }
        }
        return list;
    }
}