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