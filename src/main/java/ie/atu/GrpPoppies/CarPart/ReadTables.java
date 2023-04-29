package ie.atu.GrpPoppies.CarPart;

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
                System.out.printf("%-20s", rsmd.getColumnName(i));//have a look at this replaces \t\t
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
    public static String listTables() {//only parts
        String table = null;
        try {
            Connection conn = DatabaseUtils.getConnection();

            DatabaseMetaData data = conn.getMetaData();
            ResultSet rs = data.getTables(null, null, null, new String[]{"TABLE"});
            ArrayList<String> tables = new ArrayList<>();
            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!tableName.equals("trace_xe_action_map") && !tableName.equals("trace_xe_event_map") && !tableName.equals("Customer")) {//IDK what these are but it returns this
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

    public static void listColumns() {//list column names
        try {
            Connection conn = DatabaseUtils.getConnection();
            String tableGet = listTables();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, tableGet, null);

            ArrayList<String> columnNames = new ArrayList<>();

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if (!columnName.equals("Internal_ID")) {// is the auto increment and will return
                    columnNames.add(columnName);
                }
            }
            for (String columnName : columnNames) {
                System.out.println(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



