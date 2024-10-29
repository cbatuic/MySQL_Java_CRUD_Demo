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

-- Insert dummy data into `students` table
INSERT INTO students (student_name, student_id, course, year_level) VALUES
('Alice Johnson', 'S1001', 'Computer Science', 2),
('Bob Smith', 'S1002', 'Information Technology', 3),
('Carol Williams', 'S1003', 'Software Engineering', 1),
('David Brown', 'S1004', 'Information Systems', 4),
('Eve Davis', 'S1005', 'Computer Science', 2);

-- Insert dummy data into `attendance` table
INSERT INTO attendance (student_id, check_in, check_out) VALUES
(1, '2024-10-01 08:00:00', '2024-10-01 12:00:00'),
(1, '2024-10-02 08:05:00', '2024-10-02 12:15:00'),
(2, '2024-10-01 09:00:00', '2024-10-01 12:00:00'),
(2, '2024-10-02 09:10:00', '2024-10-02 12:10:00'),
(3, '2024-10-01 08:30:00', '2024-10-01 11:30:00'),
(3, '2024-10-02 08:35:00', '2024-10-02 11:45:00'),
(4, '2024-10-01 10:00:00', '2024-10-01 14:00:00'),
(5, '2024-10-01 08:15:00', '2024-10-01 12:30:00'),
(5, '2024-10-02 08:20:00', '2024-10-02 12:25:00');


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
