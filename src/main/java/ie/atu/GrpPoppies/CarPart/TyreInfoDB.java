package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;

public class TyreInfoDB {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";
    public static void savetoDatabase(String description, String warranty, double price, String supplier, String manufacturer, String name, double partNumber, int quantity, int tyreSize, String tyreRating, String tyreType) {
        TyreInfo Tpart = new TyreInfo();
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
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "Wheel_info", null);

            ArrayList<String> columnNames = new ArrayList<>();

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if (!columnName.equals("Internal_ID")) {// is the auto increment and will return
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
            String query = "INSERT INTO wheel_info (" + columnGet + ") VALUES (" + columnValues + ")";
            PreparedStatement stmt = conn.prepareStatement(query);

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
}

