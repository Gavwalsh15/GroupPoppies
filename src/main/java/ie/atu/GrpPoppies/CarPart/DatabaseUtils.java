/*
package ie.atu.GrpPoppies.CarPart;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtils {
    //later we will look at storing this type of data in a better location like a properties file
    private static final String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    private static final String username = "CloudSAe622a702@carpartserver";
    private static final String password = "GroupPoppies2023";

    private static final DataSource Data;

    //notice the static has no name?
    //The static block does not have a method name because it is a special block of code that
    // is executed when the class is loaded into memory. It is used to initialize static variables and perform
    // any other one-time setup that the class may require.
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(url);
        mysqlDataSource.setUser(username);
        mysqlDataSource.setPassword(password);
        Data = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return Data.getConnection();
    }
}
*/


package ie.atu.GrpPoppies.CarPart;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String url = "carpartserver.database.windows.net";
    private static final String username = "CloudSAe622a702@carpartserver";
    private static final String password = "GroupPoppies2023";
    private static final String database = "CarParts";

    private static final SQLServerDataSource Data;

    static {
        Data = new SQLServerDataSource();
        Data.setServerName(url);
        Data.setUser(username);
        Data.setPassword(password);
        Data.setDatabaseName(database);
    }

    public static Connection getConnection() throws SQLException {
        return Data.getConnection();
    }
}