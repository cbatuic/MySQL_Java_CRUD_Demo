import java.sql.*;

public class AttendanceDAO {

    public void checkIn(int studentId) throws SQLException {
        String query = "INSERT INTO attendance (student_id, check_in) VALUES (?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }

    public void checkOut(int studentId) throws SQLException {
        String query = "UPDATE attendance SET check_out = NOW() WHERE student_id = ? AND check_out IS NULL";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        }
    }

    public ResultSet getAttendanceHistory(int studentId) throws SQLException {
        String query = "SELECT * FROM attendance WHERE student_id = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, studentId);
        return stmt.executeQuery();
    }
}
