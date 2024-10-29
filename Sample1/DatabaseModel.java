import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseModel {
    private Connection connection;

    public DatabaseModel() {
        try {
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/your_database";
            String user = "your_username";
            String password = "your_password";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> getAllRecords() {
        List<String[]> records = new ArrayList<>();
        String query = "SELECT * FROM your_table";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String[] record = new String[2]; // Adjust the size based on your table
                record[0] = rs.getString("column1");
                record[1] = rs.getString("column2");
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void addRecord(String column1, String column2) {
        String query = "INSERT INTO your_table (column1, column2) VALUES (?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, column1);
            pstmt.setString(2, column2);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRecord(String column1, String column2) {
        String query = "UPDATE your_table SET column2 = ? WHERE column1 = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, column2);
            pstmt.setString(2, column1);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(String column1) {
        String query = "DELETE FROM your_table WHERE column1 = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, column1);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
