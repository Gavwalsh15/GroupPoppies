package ie.atu.GrpPoppies.CarPart.Pool;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadTables {
    public static void viewParts() {
        String tableGet = ReadTables.listTables();
        try {
            Connection conn = DatabaseUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableGet);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-20s", rsmd.getColumnName(i));//makes a cool looking table
            }
            System.out.println("\n");
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving car parts: " + e.getMessage());
        }
    }
    public static String listTables() {
        String table = null;
        try {
            Connection conn = DatabaseUtils.getConnection();

            DatabaseMetaData data = conn.getMetaData();
            ResultSet rs = data.getTables(null, null, null, new String[]{"TABLE"});
            ArrayList<String> tables = new ArrayList<>();
            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!tableName.equals("trace_xe_action_map") && !tableName.equals("trace_xe_event_map") && !tableName.equals("Customer") && !tableName.equals("CarOils")) {//IDK what these are but it returns this
                    tables.add(tableName);
                }
            }

            Scanner scanner = new Scanner(System.in);
            for (int p = 0; p < tables.size(); p++) {
                System.out.println(p + 1 + ". " + tables.get(p));
            }
            System.out.println("Enter the part category:");
            int tableInd = scanner.nextInt();//ind == index?? good job gavin
            table = tables.get(tableInd - 1);//tables start at 0

        } catch (SQLException e) {
            System.out.println("Error listing tables: " + e.getMessage());
        }
        return table;
    }

    public static String listColumns(String tableGet) {
        String column = null;
        int p = 0;
        try {
            Connection conn = DatabaseUtils.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableGet, null);

            ArrayList<String> columnNames = new ArrayList<>();

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                columnNames.add(columnName);
            }
            Scanner scanner = new Scanner(System.in);
            for (p = 0; p < columnNames.size(); p++) {
                System.out.println(p + 1 + ". " + columnNames.get(p));
            }
            System.out.println(p+1 + ". Search all" );
            System.out.println("Enter the category:");
            int tableInd = scanner.nextInt();
            if(tableInd > columnNames.size()){
                column = "All";
            }else{column = columnNames.get(tableInd - 1);}

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return column;
    }
}



