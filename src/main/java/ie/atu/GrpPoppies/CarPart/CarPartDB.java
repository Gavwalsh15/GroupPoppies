package ie.atu.GrpPoppies.CarPart;


import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CarPartDB {
    public static void savetoDatabase(String description, String warranty, double price, String supplier, String manufacturer, String name, double partNumber, int quantity) {
        CarPart part = new CarPart();

        part.setPartNumber(partNumber);
        part.setName(name);
        part.setManufacturer(manufacturer);
        part.setSupplier(supplier);
        part.setQuantity(quantity);
        part.setPrice(price);
        part.setWarranty(warranty);
        part.setDescription(description);

        try {
            Connection conn = DatabaseUtils.getConnection();

            // get column names from the carpart table
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "Car_Parts", null);

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
            String query = "INSERT INTO Car_Parts (" + columnGet + ") VALUES (" + columnValues + ")";
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
        return;
    }

    public static void updatePart(int partNumber) {
        try {
            Scanner scanner = new Scanner(System.in);

            Connection conn = DatabaseUtils.getConnection();

            String query = "SELECT * FROM Car_Parts WHERE part_number=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, partNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing car part object
                CarPart part = new CarPart();
                part.setPartNumber(rs.getInt("part_number"));
                part.setName(rs.getString("name"));
                part.setManufacturer(rs.getString("manufacturer"));
                part.setSupplier(rs.getString("supplier"));
                part.setQuantity(rs.getInt("quantity"));
                part.setPrice(rs.getDouble("price"));
                part.setWarranty(rs.getString("warranty"));
                part.setDescription(rs.getString("description"));

                // update fields of car part object
                System.out.println("Enter new part name:");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    part.setName(name);
                }

                System.out.println("Enter new part manufacturer:");
                String manufacturer = scanner.nextLine();
                if (!manufacturer.isEmpty()) {
                    part.setManufacturer(manufacturer);
                }

                System.out.println("Enter new part supplier:");
                String supplier = scanner.nextLine();
                if (!supplier.isEmpty()) {
                    part.setSupplier(supplier);
                }

                System.out.println("Enter new part quantity:");
                String quantityStr = scanner.nextLine();
                if (!quantityStr.isEmpty()) {
                    int quantity = parseInt(quantityStr);
                    part.setQuantity(quantity);
                }

                System.out.println("Enter new part price:");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    part.setPrice(price);
                }

                System.out.println("Enter new part warranty:");
                String warranty = scanner.nextLine();
                if (!warranty.isEmpty()) {
                    part.setWarranty(warranty);
                }

                System.out.println("Enter new part description:");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    part.setDescription(description);
                }

                // save updated car part object to database
                String updateQuery = "UPDATE Car_Parts SET part_number=?, name=?, manufacturer=?, supplier=?, quantity=?, price=?, warranty=?, description=? WHERE Intenal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setDouble(1, part.getPartNumber());
                updateStmt.setString(2, part.getName());
                updateStmt.setString(3, part.getManufacturer());
                updateStmt.setString(4, part.getSupplier());
                updateStmt.setInt(5, part.getQuantity());
                updateStmt.setDouble(6, part.getPrice());
                updateStmt.setString(7, part.getWarranty());
                updateStmt.setString(8, part.getDescription());
                updateStmt.setInt(9, partNumber);
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Car part updated successfully.");
                } else {
                    System.out.println("Failed to update car part.");
                }
            } else {
                System.out.println("Car part not found.");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error Updating part: " + e.getMessage());
        }
    }

    //added interfacing
    //dont know how to add search function
    /*
    public static void addCarPart() {
    }
    public static void updateCarPart() {
    }
    public static void deleteCarPart() {
    */
    }

