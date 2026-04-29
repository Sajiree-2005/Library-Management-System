package dao;

import db.DBConnection;
import java.sql.*;
import java.time.LocalDate;

public class IssueDAO {

    public void issueBook(int memberId, int bookId) throws Exception {

        Connection con = DBConnection.getConnection();

        try {
            con.setAutoCommit(false);

            // check book availability
            PreparedStatement check = con.prepareStatement(
                    "SELECT available_copies FROM Book WHERE book_id=?");
            check.setInt(1, bookId);
            ResultSet rs = check.executeQuery();

            if (!rs.next() || rs.getInt(1) <= 0)
                throw new Exception("Book not available");

            // insert issue
            LocalDate now = LocalDate.now();

            PreparedStatement issue = con.prepareStatement(
                    "INSERT INTO Issue(member_id,book_id,issue_date,due_date) VALUES (?,?,?,?)");

            issue.setInt(1, memberId);
            issue.setInt(2, bookId);
            issue.setDate(3, Date.valueOf(now));
            issue.setDate(4, Date.valueOf(now.plusDays(7)));

            issue.executeUpdate();

            // update copies
            PreparedStatement update = con.prepareStatement(
                    "UPDATE Book SET available_copies=available_copies-1 WHERE book_id=?");

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