package dao;

import db.DBConnection;
import model.Book;
import java.sql.*;
import java.util.*;

public class BookDAO {

    public void addBook(Book b) throws Exception {

        if (b.title == null || b.title.isEmpty())
            throw new Exception("Title required");

        if (b.author == null || b.author.isEmpty())
            throw new Exception("Author required");

        if (b.totalCopies < 0 || b.availableCopies < 0 || b.availableCopies > b.totalCopies)
            throw new Exception("Invalid copies");

        String sql = "INSERT INTO Book(title,author,publisher,isbn,category,total_copies,available_copies,shelf_location,created_at) VALUES (?,?,?,?,?,?,?,?,NOW())";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.title);
            ps.setString(2, b.author);
            ps.setString(3, b.publisher);
            ps.setString(4, b.isbn);
            ps.setString(5, b.category);
            ps.setInt(6, b.totalCopies);
            ps.setInt(7, b.availableCopies);
            ps.setString(8, b.shelfLocation);

            ps.executeUpdate();
        }
    }

    public List<Book> searchByTitle(String title) throws Exception {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT * FROM Book WHERE title LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Book b = new Book();
                b.bookId = rs.getInt("book_id");
                b.title = rs.getString("title");
                list.add(b);
            }
        }
        return list;
    }
}