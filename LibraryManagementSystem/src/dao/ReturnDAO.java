package dao;

import db.DBConnection;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class ReturnDAO {
    double calculateFine(Date due, Date ret) {
        long days = ChronoUnit.DAYS.between(due.toLocalDate(), ret.toLocalDate());
        return Math.max(0, days * 10);
    }

    public void returnBook(int issueId) throws Exception {

        Connection con = DBConnection.getConnection();

        try {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(
                    "SELECT book_id,due_date FROM Issue WHERE issue_id=?");

            ps.setInt(1, issueId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) throw new Exception("Invalid issue");

            int bookId = rs.getInt("book_id");
            Date due = rs.getDate("due_date");
            Date ret = Date.valueOf(LocalDate.now());

            double fine = calculateFine(due, ret);

            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO Return_Book(issue_id,return_date,fine) VALUES (?,?,?)");

            insert.setInt(1, issueId);
            insert.setDate(2, ret);
            insert.setDouble(3, fine);
            insert.executeUpdate();

            PreparedStatement update = con.prepareStatement(
                    "UPDATE Book SET available_copies=available_copies+1 WHERE book_id=?");

            update.setInt(1, bookId);
            update.executeUpdate();

            con.commit();

        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            con.close();
        }
    }
}