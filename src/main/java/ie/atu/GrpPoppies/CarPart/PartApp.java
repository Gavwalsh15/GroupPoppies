package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PartApp {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";

    public static void main(String[] args) {
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Display the menu
            int choice = 0;


            while (choice != 4) {
                System.out.println("Car and Engine Parts Management System");
                System.out.println("1. Add Car Part");
                System.out.println("2. View All Parts");
                System.out.println("3. Delete Part");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 1 -> addCarPart();
                    case 2 -> viewParts();
                    case 3 -> deletePart();
                    case 4 -> System.out.println("Well done no Errors I hope!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }

                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deletePart() {
        Scanner scanner = new Scanner(System.in);

        String tableGet = listTables();
        System.out.println("Enter part number to delete:");
        int partNumber = scanner.nextInt();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + tableGet + " WHERE part_number = ?");
            stmt.setInt(1, partNumber);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No part found with part number " + partNumber);
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting part: " + e.getMessage());
        }
    }


    private static void viewParts() {
        String tableGet = listTables();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableGet);

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            System.out.println(numColumns);
            while (rs.next()){
                for (int i = 1; i <= numColumns; i++) {
                    System.out.print(rsmd.getColumnName(i) + ": " + rs.getString(i) + "\t");
                }
                System.out.println("\n");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving car parts: " + e.getMessage());
        }
    }


    private static void addCarPart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a part to:");
        listTables();
        System.out.println("4. Back");//change this when you think of how
        int choice = scanner.nextInt();

        while (choice != 4) {
            switch (choice) {
                case 1 -> System.out.println("Enter car part details:");
                case 2 -> System.out.println("Enter engine part details:");
                case 3 -> System.out.println("Enter Wheel details:");
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Enter part number:");
            double partNumber = scanner.nextDouble();
            scanner.nextLine(); // issue going from double to string when taking values

            System.out.println("Enter part name:");
            String name = scanner.nextLine();

            System.out.println("Enter part manufacturer:");
            String manufacturer = scanner.nextLine();

            System.out.println("Enter part supplier:");
            String supplier = scanner.nextLine();

            System.out.println("Enter part quantity:");
            int quantity = scanner.nextInt();

            System.out.println("Enter part price:");
            double price = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Enter part warranty:");
            String warranty = scanner.nextLine();

            System.out.println("Enter part description:");
            String description = scanner.nextLine();

            if (choice == 1) {
                CarPart part = new CarPart();
                part.setPartNumber(partNumber);
                part.setName(name);
                part.setManufacturer(manufacturer);
                part.setSupplier(supplier);
                part.setQuantity(quantity);
                part.setPrice(price);
                part.setWarranty(warranty);
                part.setDescription(description);
                savetoDatabase(part);
            } else if (choice == 2) {//engine
                EnginePart Epart = new EnginePart();
                System.out.println("Enter Engine Type:");
                String engineType = scanner.nextLine();

                System.out.println("Enter Engine Size:");
                int engineSize = scanner.nextInt();
                Epart.setEngineSize(engineSize);

                Epart.setPartNumber(partNumber);
                Epart.setName(name);
                Epart.setManufacturer(manufacturer);
                Epart.setSupplier(supplier);
                Epart.setQuantity(quantity);
                Epart.setPrice(price);
                Epart.setWarranty(warranty);
                Epart.setDescription(description);
                Epart.setEngineType(engineType);
                Epart.setEngineSize(engineSize);
                savetoDatabase(Epart);
            } else if (choice == 3) {//tyre
                TyreInfo Tpart = new TyreInfo();
                System.out.println("Enter Tyre Type:");
                String tyreType = scanner.nextLine();

                System.out.println("Enter tyre Size:");
                int tyreSize = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter Tyre Rating:");
                String tyreRating = scanner.nextLine();

                Tpart.setPartNumber(partNumber);
                Tpart.setName(name);
                Tpart.setManufacturer(manufacturer);
                Tpart.setSupplier(supplier);
                Tpart.setQuantity(quantity);
                Tpart.setPrice(price);
                Tpart.setWarranty(warranty);
                Tpart.setDescription(description);
                Tpart.setTyreType(tyreType);
                Tpart.setTyreRating(tyreRating);
                Tpart.setWheelSize(tyreSize);
                savetoDatabase(Tpart);
            }
            System.out.println("Part added:\n");

            System.out.println("Add a part to:");
            listTables();
            System.out.println("4. Back");
            choice = scanner.nextInt();
        }
    }


    private static void savetoDatabase(CarPart part) {
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // get column names from the carpart table
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "carpart", null);

            ArrayList<String> columnNames = new ArrayList<>();

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                // skip IDENTITY column as it's auto-incremented
                if (!columnName.equals("Internal_ID")) {// is the auto increment
                    columnNames.add(columnName);
                }
            }
            String columnGet = String.join(",", columnNames);
            String columnValues = "";
            for (int i = 0; i < columnNames.size(); i++) {
                columnValues += "?";//adds ? for every var
                if (i != columnNames.size() - 1) {//stop extra , at end
                    columnValues += ",";
                }
            }
            String query = "INSERT INTO carpart (" + columnGet + ") VALUES (" + columnValues + ")";
            PreparedStatement stmt = conn.prepareStatement(query);
            // set the parameters for the PreparedStatement
            stmt.setDouble(1, part.getPartNumber());
            stmt.setString(2, part.getName());
            stmt.setString(3, part.getManufacturer());
            stmt.setString(4, part.getSupplier());
            stmt.setInt(5, part.getQuantity());
            stmt.setDouble(6, part.getPrice());
            stmt.setString(7, part.getWarranty());
            stmt.setString(8, part.getDescription());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Car part saved to database.");
            } else {
                System.out.println("Failed to save car part to database.");
            }

            // close the connection to the database
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error saving car part to database: " + e.getMessage());
        }
    }


    private static void savetoDatabase(EnginePart Epart) {
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO engine_parts (part_number, name, manufacturer, supplier, quantity, price, warranty, " +
                    "description, engine_size, engine_type) VALUES (?,?,?,?,?,?,?,?,?,?)");

            // set the parameters for the PreparedStatement
            stmt.setDouble(1, Epart.getPartNumber());
            stmt.setString(2, Epart.getName());
            stmt.setString(3, Epart.getManufacturer());
            stmt.setString(4, Epart.getSupplier());
            stmt.setInt(5, Epart.getQuantity());
            stmt.setDouble(6, Epart.getPrice());
            stmt.setString(7, Epart.getWarranty());
            stmt.setString(8, Epart.getDescription());
            stmt.setDouble(9, Epart.getEngineSize());
            stmt.setString(10, Epart.getEngineType());


            // execute the insert statement
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Car part saved to database.");
            } else {
                System.out.println("Failed to save car part to database.");
            }

            // close the connection to the database
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error saving car part to database: " + e.getMessage());
        }
    }

    private static void savetoDatabase(TyreInfo Tpart) {
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO wheel_info (part_number, name, manufacturer, supplier, quantity, price, " +
                    "warranty, description, tyretype, tyrerating, wheelsize) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            // set the parameters for the PreparedStatement
            stmt.setDouble(1, Tpart.getPartNumber());
            stmt.setString(2, Tpart.getName());
            stmt.setString(3, Tpart.getManufacturer());
            stmt.setString(4, Tpart.getSupplier());
            stmt.setInt(5, Tpart.getQuantity());
            stmt.setDouble(6, Tpart.getPrice());
            stmt.setString(7, Tpart.getWarranty());
            stmt.setString(8, Tpart.getDescription());
            stmt.setString(9, Tpart.getTyreType());
            stmt.setString(10, Tpart.getTyreRating());
            stmt.setDouble(11, Tpart.getWheelSize());

            // execute the insert statement
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Car part saved to database.");
            } else {
                System.out.println("Failed to save car part to database.");
            }

            // close the connection to the database
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error saving car part to database: " + e.getMessage());
        }
    }

    private static String listTables() {
        int i = 1;
        String table = null;
        ArrayList<String> tables = null;
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            DatabaseMetaData data = conn.getMetaData();
            ResultSet rs = data.getTables(null, null, null, new String[]{"TABLE"});
            tables = new ArrayList<>();
            System.out.println("Tables in the database:");
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                if (!tableName.equals("trace_xe_action_map") && !tableName.equals("trace_xe_event_map")) {
                    tables.add(tableName);
                }
            }

            Scanner scanner = new Scanner(System.in);
            for(int p = 0; p < tables.size(); p++){
                System.out.println(p+1 + ". " + tables.get(p));
            }
            System.out.println("Enter the part category:");
            int tableInd = scanner.nextInt();
            table = tables.get(tableInd - 1);//tables start at 0

        } catch (SQLException e) {
            System.out.println("Error listing tables: " + e.getMessage());
        }
        return table;
    }
}







