package service;

import dao.*;
import model.*;

import java.util.List;

public class LibraryService {

    private BookDAO bookDAO = new BookDAO();
    private MemberDAO memberDAO = new MemberDAO();
    private IssueDAO issueDAO = new IssueDAO();
    private ReturnDAO returnDAO = new ReturnDAO();
    private AdminDAO adminDAO = new AdminDAO();
    private AuditDAO auditDAO = new AuditDAO();

    // ---------------- BOOK ----------------
    public void addBook(Book b) throws Exception {
        if (b == null) throw new Exception("Book cannot be null");
        bookDAO.addBook(b);
    }

    public List<Book> searchBook(String title) throws Exception {
        if (title == null || title.isEmpty())
            throw new Exception("Enter title to search");
        return bookDAO.searchByTitle(title);
    }

    // ---------------- MEMBER ----------------
    public void addMember(Member m) throws Exception {
        if (m == null) throw new Exception("Member cannot be null");
        memberDAO.addMember(m);
    }

    public Member getMember(int id) throws Exception {
        if (id <= 0) throw new Exception("Invalid member ID");
        return memberDAO.getMemberById(id);
    }

    // ---------------- ISSUE ----------------
    public void issueBook(int memberId, int bookId) throws Exception {

        if (memberId <= 0 || bookId <= 0)
            throw new Exception("Invalid IDs");

        Member m = memberDAO.getMemberById(memberId);

        if (m == null)
            throw new Exception("Member not found");

        if (!m.status.equalsIgnoreCase("ACTIVE"))
            throw new Exception("Member is not ACTIVE");

        issueDAO.issueBook(memberId, bookId);
    }

    // ---------------- RETURN ----------------
    public void returnBook(int issueId) throws Exception {

        if (issueId <= 0)
            throw new Exception("Invalid issue ID");

        returnDAO.returnBook(issueId);
    }

    // ---------------- ADMIN ----------------
    public boolean login(String username, String password) throws Exception {

        if (username == null || password == null ||
            username.isEmpty() || password.isEmpty())
            throw new Exception("Invalid login input");

        return adminDAO.login(username, password);
    }

    // ---------------- AUDIT ----------------
    public List<Audit> getAuditLogs() throws Exception {
        return auditDAO.getAuditLogs();
    }
}