package ie.atu.GrpPoppies.CarPart.Pool;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    private static final String username = "CloudSAe622a702@carpartserver";
    private static final String password = "GroupPoppies2023";

    private static final DataSource dataSource;

    static {
        SQLServerDataSource sqlDS = new SQLServerDataSource();
        sqlDS.setURL(url);
        sqlDS.setUser(username);
        sqlDS.setPassword(password);
        dataSource = sqlDS;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
