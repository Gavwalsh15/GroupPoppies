package ie.atu.GrpPoppies.standard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String[] args) {

        Connection connection = null;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts;user=CloudSAe622a702@carpartserver;password=GroupPoppies2023;");
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
