package ie.atu.GrpPoppies.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateExample {
    public static void main(String[] args) {
        String updateSQL = "UPDATE users SET password = 'newpassword' WHERE username = 'Des'";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement()) {
            int rowsUpdated = statement.executeUpdate(updateSQL);
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
