package main;

import service.LibraryService;
import model.*;
import java.sql.Date;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();
        try {
            while (true) {
                System.out.println("\n===== LIBRARY MENU =====");
                System.out.println("1. Add Member");
                System.out.println("2. Issue Book");
                System.out.println("3. Return Book");
                System.out.println("4. Search Book");
                System.out.println("5. View Audit Logs");
                System.out.println("6. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // clear buffer
                switch (choice) {
                    // ---------------- ADD MEMBER ----------------
                    case 1:
                        Member m = new Member();

                        System.out.print("Enter Name: ");
                        m.name = sc.nextLine();

                        System.out.print("Enter Email: ");
                        m.email = sc.nextLine();

                        System.out.print("Enter Phone: ");
                        m.phone = sc.nextLine();

                        m.membershipDate = new Date(System.currentTimeMillis());
                        m.status = "ACTIVE";

                        service.addMember(m);
                        System.out.println("Member added successfully!");
                        break;

                    // ---------------- ISSUE BOOK ----------------
                    case 2:
                        System.out.print("Enter Member ID: ");
                        int memId = sc.nextInt();

                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();

                        service.issueBook(memId, bookId);
                        System.out.println("Book issued successfully!");
                        break;

                    // ---------------- RETURN BOOK ----------------
                    case 3:
                        System.out.print("Enter Issue ID: ");
                        int issueId = sc.nextInt();

                        service.returnBook(issueId);
                        System.out.println("Book returned successfully!");
                        break;

                    // ---------------- SEARCH BOOK ----------------
                    case 4:
                        sc.nextLine();
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();

                        List<Book> books = service.searchBook(title);

                        for (Book b : books) {
                            System.out.println("ID: " + b.bookId + " | Title: " + b.title);
                        }
                        break;

                    // ---------------- AUDIT LOGS ----------------
                    case 5:
                        List<Audit> logs = service.getAuditLogs();
                        for (Audit a : logs) {
                            System.out.println(a.tableName + " - " + a.operationType);
                        }
                        break;

                    // ---------------- EXIT ----------------
                    case 6:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}