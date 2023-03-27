package ie.atu.GrpPoppies.standard;
import java.sql.*;

public class InsertTransactionExample {

        public static void main(String[] args) throws SQLException {

            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts;user=CloudSAe622a702@carpartserver;password=GroupPoppies2023;");

            try {
                // Set auto-commit to false to start a transaction
                conn.setAutoCommit(false);

                // Insert a new record into the "users" table
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                stmt.setString(1, "Paul");
                stmt.setString(2, "qwerty");
                stmt.executeUpdate();

                // Retrieve the generated key for the new record
                // This is another way of retrieving a generated key from a table
                /* ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int lastInsertId = rs.getInt(1);
                    System.out.println("Last inserted ID: " + lastInsertId);
                }

                 */

                // Insert a new record into the "emails" table, referencing the new user
                stmt = conn.prepareStatement("INSERT INTO emails (user_id, email) VALUES (?, ?)");
                stmt.setInt(1, getLastInsertId(conn));
                stmt.setString(2, "paul@atu.com");
                stmt.executeUpdate();

                // Commit the transaction
                conn.commit();

                System.out.println("Transaction completed successfully.");
            } catch (SQLException ex) {
                // If there is an error, rollback the transaction
                conn.rollback();

                System.out.println("Transaction failed.");
                ex.printStackTrace();
            } finally {
                // Set auto-commit back to true to end the transaction
                conn.setAutoCommit(true);
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