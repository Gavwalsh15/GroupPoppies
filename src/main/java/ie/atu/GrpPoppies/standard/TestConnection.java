package ie.atu.GrpPoppies.standard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        Connection connection = null;
        try
        {
            // Load the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/exampledatabase", "root", "password");
            System.out.println("Connection made to connection pool");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally
        {
            // Close the connection when finished
            if (connection != null) {
                try
                {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
