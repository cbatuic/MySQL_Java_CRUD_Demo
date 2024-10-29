import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentName(rs.getString("student_name"));
                student.setStudentId(rs.getString("student_id"));
                student.setCourse(rs.getString("course"));
                student.setYearLevel(rs.getInt("year_level"));
                students.add(student);
            }
        }
        return students;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (student_name, student_id, course, year_level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getStudentId());
            stmt.setString(3, student.getCourse());
            stmt.setInt(4, student.getYearLevel());
            stmt.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET student_name = ?, course = ?, year_level = ? WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getCourse());
            stmt.setInt(3, student.getYearLevel());
            stmt.setString(4, student.getStudentId());
            stmt.executeUpdate();
        }
    }

    public void deleteStudent(String studentId) throws SQLException {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, studentId);
            stmt.executeUpdate();
        }
    }
}
