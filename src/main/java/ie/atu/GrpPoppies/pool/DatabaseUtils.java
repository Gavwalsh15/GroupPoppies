package ie.atu.GrpPoppies.pool;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtils {
    //later we will look at storing this type of data in a better location like a properties file
    private static final String URL = "jdbc:mysql://localhost:3306/exampledatabase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";
    private static final DataSource dataSource;

    //notice the static has no name?
    //The static block does not have a method name because it is a special block of code that
    // is executed when the class is loaded into memory. It is used to initialize static variables and perform
    // any other one-time setup that the class may require.
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(URL);
        mysqlDataSource.setUser(USERNAME);
        mysqlDataSource.setPassword(PASSWORD);
        dataSource = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}