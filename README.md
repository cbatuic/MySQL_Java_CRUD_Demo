# MySQL_Java_CRUD_Demo

```sql
# Setup Database

CREATE DATABASE attendance_monitoring;

USE attendance_monitoring;

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100) NOT NULL,
    student_id VARCHAR(50) NOT NULL UNIQUE,
    course VARCHAR(50),
    year_level INT
);

CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    check_in TIMESTAMP NULL,
    check_out TIMESTAMP NULL,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
);

```

```java
// Basic Codes for GUI

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class AttendanceApp extends JFrame {
    private JTable studentTable;
    private JTextField searchField;
    private JButton addButton, editButton, deleteButton, checkInButton, checkOutButton;
    private StudentDAO studentDAO;
    private AttendanceDAO attendanceDAO;

    public AttendanceApp() {
        // Initialize components and DAO
        studentDAO = new StudentDAO();
        attendanceDAO = new AttendanceDAO();

        // Load initial data
        loadStudentTable();
    }

    private void loadStudentTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.setRowCount(0);
            for (Student student : studentDAO.getAllStudents()) {
                model.addRow(new Object[]{student.getId(), student.getStudentName(), student.getStudentId(), student.getCourse(), student.getYearLevel()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Action methods for adding, editing, deleting, checking in, and checking out students...
}


```
