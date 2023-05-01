package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadTablesCustomer {
    public static void viewCustomers() {
        String tableGetCustomer = ReadTablesCustomer.listTablesC();
        try {
            Connection conn = DatabaseUtils.getConnection();

            Statement stmtC = conn.createStatement();
            ResultSet rsC = stmtC.executeQuery("SELECT * FROM " + tableGetCustomer);

            ResultSetMetaData rsmdC = rsC.getMetaData();
            int numColumns = rsmdC.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-20s", rsmdC.getColumnName(i));//have a look at this replaces \t\t
            }
            System.out.println("\n");
            while (rsC.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-20s", rsC.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving Customer: " + e.getMessage());
        }
    }
    public static String listTablesC() {//only parts
        String tableC = null;
        try {
            Connection conn = DatabaseUtils.getConnection();

            DatabaseMetaData data = conn.getMetaData();
            ResultSet rsC = data.getTables(null, null, null, new String[]{"TABLE"});
            ArrayList<String> tablesC = new ArrayList<>();
            System.out.println("Tables in the database:");
            while (rsC.next()) {
                String tableNameC = rsC.getString("TABLE_NAME");
                if (!tableNameC.equals("trace_xe_action_map") && !tableNameC.equals("trace_xe_event_map")) {            //changed this idk what it is
                    tablesC.add(tableNameC);
                }

            }

            Scanner scanner = new Scanner(System.in);
            for (int p = 0; p < tablesC.size(); p++) {
                System.out.println(p + 1 + ". " + tablesC.get(p));
            }
            System.out.println("Enter the part category:");
            int tableIndC = scanner.nextInt();//ind == index?? good job gavin
            tableC = tablesC.get(tableIndC - 1);//tables start at 0

        } catch (SQLException e) {
            System.out.println("Error listing tables: " + e.getMessage());
        }
        return tableC;
    }

    public static void listColumns() {      //list column names
        try {
            Connection conn = DatabaseUtils.getConnection();
            String tableGetC = listTablesC();
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columnsC = metaData.getColumns(null, null, tableGetC, null);

            ArrayList<String> columnNamesC = new ArrayList<>();

            while (columnsC.next()) {
                String columnNameC = columnsC.getString("COLUMN_NAME");
                if (!columnNameC.equals("Internal_ID")) {// is the auto increment and will return
                    columnNamesC.add(columnNameC);
                }
            }
            for (String columnNameC : columnNamesC) {
                System.out.println(columnNameC);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



