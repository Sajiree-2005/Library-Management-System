# 📚 Online Library Management System

A Java and MySQL-based Library Management System that demonstrates core DBMS concepts such as Views, Indexes, Triggers, and Transactions, utilizing a layered Java architecture with JDBC and a Swing GUI frontend.

---

## 🚀 Features

*   🔐 **Admin Login System:** Secure access for authorized personnel.
*   👤 **Member Management:** Add, update, and track library members.
*   📖 **Book Management:** Comprehensive CRUD operations for the book catalog.
*   📥 **Issue & Return:** Streamlined book circulation.
*   🔁 **Fine Calculation:** Automatic fine generation upon late returns.
*   🔍 **Optimized Search:** Search books by title using database **Indexing**.
*   🧾 **Audit Logging:** Automated tracking of changes via **Triggers**.
*   👁️ **Issued Books View:** A dedicated **View** for currently borrowed items.
*   🎨 **Interactive GUI:** User-friendly interface built with Java Swing.

---

## 🧠 DBMS Concepts Implemented

1.  **Tables:** Structured storage for Books, Members, Issues, Returns, Admins, and Audits.
2.  **Constraints:** Robust data integrity using Primary Keys, Foreign Keys, `NOT NULL`, and `UNIQUE` constraints.
3.  **Index:** `CREATE INDEX idx_book_title ON Book(title);` to improve search performance.
4.  **View:** `IssuedBooksView` simplifies complex joins for reporting.
5.  **Triggers:** Automated audit logging for `UPDATE` and `DELETE` operations.
6.  **Transactions:** Ensures **ACID** properties during Issue and Return operations.

---

## 💻 Tech Stack

*   **Frontend:** Java Swing (GUI)
*   **Backend:** Java
*   **Database:** MySQL
*   **Connectivity:** JDBC

---

## 🏗️ Project Structure

```text
library/
├── db/          # Database connection logic
├── model/       # Plain Old Java Objects (POJOs)
├── dao/         # Data Access Objects (SQL logic)
├── service/     # Business logic layer
├── gui/         # Java Swing frames and panels
└── main/        # Application entry point
```

---

## 🔌 Database Setup (MySQL)

### 1. Create Database
```sql
CREATE DATABASE LibraryManagementSystem;
USE LibraryManagementSystem;
```

### 2. Create Tables
<details>
<summary>Click to expand Table Schemas</summary>

```sql
CREATE TABLE Book (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    publisher VARCHAR(255),
    isbn VARCHAR(20),
    category VARCHAR(100),
    total_copies INT NOT NULL,
    available_copies INT NOT NULL,
    shelf_location VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Member (
    member_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    phone VARCHAR(10),
    membership_date DATE,
    status VARCHAR(20)
);

CREATE TABLE Issue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT,
    book_id INT,
    issue_date DATE,
    due_date DATE,
    FOREIGN KEY (member_id) REFERENCES Member(member_id),
    FOREIGN KEY (book_id) REFERENCES Book(book_id)
);

CREATE TABLE Return_Book (
    return_id INT PRIMARY KEY AUTO_INCREMENT,
    issue_id INT,
    return_date DATE,
    fine DOUBLE,
    FOREIGN KEY (issue_id) REFERENCES Issue(issue_id)
);

CREATE TABLE Admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

CREATE TABLE Library_Audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(50),
    operation_type VARCHAR(20),
    old_data TEXT,
    new_data TEXT,
    record_id INT,
    operation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
</details>

### 3. Advanced SQL Components
```sql
-- Create Index
CREATE INDEX idx_book_title ON Book(title);

-- Create View
CREATE VIEW IssuedBooksView AS
SELECT b.title, m.name, i.issue_date, i.due_date
FROM Issue i
JOIN Book b ON i.book_id = b.book_id
JOIN Member m ON i.member_id = m.member_id;

-- Create Trigger (Example)
DELIMITER //
CREATE TRIGGER before_book_update
BEFORE UPDATE ON Book
FOR EACH ROW
BEGIN
    INSERT INTO Library_Audit(table_name, operation_type, old_data, new_data, record_id)
    VALUES ('Book', 'UPDATE', OLD.title, NEW.title, OLD.book_id);
END //
DELIMITER ;

-- Insert Default Admin
INSERT INTO Admin(username, password) VALUES ('admin', 'admin');
```

---

## ⚙️ How to Run

1.  **Install MySQL:** Set up a local instance and run the SQL commands provided above.
2.  **JDBC Driver:** Download the [MySQL Connector/J](https://mysql.com) and add the JAR to your project classpath.
3.  **Configure:** Update your database credentials in `DBConnection.java`.
4.  **Launch:** Compile and run `gui/LoginPage.java`.

---

## 🔄 Workflow

1.  **Login:** Authenticate via the Admin panel.
2.  **Manage:** Use the Dashboard to add members or new book arrivals.
3.  **Circulation:** Issue books to members; return them to calculate fines.
4.  **Audit:** Monitor database changes through the audit log interface.

---

## 👨‍💻 Author

**Sajiree Damle**  
*Computer Engineering Student*
