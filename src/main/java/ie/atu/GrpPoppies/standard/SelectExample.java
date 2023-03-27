package ie.atu.GrpPoppies.standard;

import java.sql.*;

public class SelectExample {
    public static void main(String[] args) {
        // MySQL database connection details
        String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts;";
        String username = "CloudSAe622a702@carpartserver;";
        String password = "GroupPoppies2023;";

        // SQL statement
        String selectSQL = "SELECT u.username, u.password, e.email " +
                "FROM users u " +
                "JOIN emails e ON u.id = e.user_id";

        try (Connection connection = DriverManager.getConnection(url, username, password);
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
