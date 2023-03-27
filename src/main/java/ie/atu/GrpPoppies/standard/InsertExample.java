package ie.atu.GrpPoppies.standard;

import java.sql.*;

public class InsertExample {

    public static void main(String[] args) throws SQLException {

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/exampledatabase", "root", "password");

        try {

            // Insert a new record into the "users" table
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            stmt.setString(1, "Des");
            stmt.setString(2, "software");
            stmt.executeUpdate();

            // Insert a new record into the "emails" table, referencing the new user
            stmt = conn.prepareStatement("INSERT INTO emails (user_id, email) VALUES (?, ?)");
            stmt.setInt(1, getLastInsertId(conn));
            stmt.setString(2, "des@atu.ie");
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
        // Close the connection
        conn.close();
    }

    // Helper method to get the ID of the last inserted record
    private static int getLastInsertId(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
        rs.next();
        int id = rs.getInt(1);
        rs.close();
        stmt.close();
        return id;
    }
}