package ie.atu.GrpPoppies.pool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectExample {
    public static void main(String[] args) {
        String selectSQL = "SELECT u.username, u.password, e.email " +
                "FROM users u " +
                "JOIN emails e ON u.id = e.user_id";

        try (Connection connection = DatabaseUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String user = resultSet.getString("username");
                String pass = resultSet.getString("password");
                String email = resultSet.getString("email");

                System.out.println("Username: " + user + ", Password: " + pass + ", Email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}