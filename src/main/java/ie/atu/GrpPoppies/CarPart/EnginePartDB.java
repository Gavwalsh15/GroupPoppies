package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class EnginePartDB {
    public static void savetoDatabase(String description, String warranty, double price, String supplier, String manufacturer, String name, double partNumber, int quantity, String engineType, int engineSize) {
        EnginePart Epart = new EnginePart();
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
        try {
            Connection conn = DatabaseUtils.getConnection();

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "Engine_parts", null);

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
            String query = "INSERT INTO Engine_parts (" + columnGet + ") VALUES (" + columnValues + ")";
            PreparedStatement stmt = conn.prepareStatement(query);

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
    public static void updatePart(int Intenal_ID) {
        try {
            Scanner scanner = new Scanner(System.in);

            Connection conn = DatabaseUtils.getConnection();
            String query = "SELECT * FROM Engine_parts WHERE Intenal_ID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Intenal_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing car part object by using database
                EnginePart Epart = new EnginePart();
                Epart.setPartNumber(rs.getInt("part_number"));
                Epart.setName(rs.getString("name"));
                Epart.setManufacturer(rs.getString("manufacturer"));
                Epart.setSupplier(rs.getString("supplier"));
                Epart.setQuantity(rs.getInt("quantity"));
                Epart.setPrice(rs.getDouble("price"));
                Epart.setWarranty(rs.getString("warranty"));
                Epart.setDescription(rs.getString("description"));

                // update fields of car part object
                System.out.println("Enter new part Part Number:");
                String PartNumStr = scanner.nextLine();
                if (!PartNumStr.isEmpty()) {
                    int PartNum = parseInt(PartNumStr);
                    Epart.setPartNumber(PartNum);
                }

                System.out.println("Enter new part name:");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    Epart.setName(name);
                }

                System.out.println("Enter new part manufacturer:");
                String manufacturer = scanner.nextLine();
                if (!manufacturer.isEmpty()) {
                    Epart.setManufacturer(manufacturer);
                }

                System.out.println("Enter new part supplier:");
                String supplier = scanner.nextLine();
                if (!supplier.isEmpty()) {
                    Epart.setSupplier(supplier);
                }

                System.out.println("Enter new part quantity:");
                String quantityStr = scanner.nextLine();
                if (!quantityStr.isEmpty()) {
                    int quantity = parseInt(quantityStr);
                    Epart.setQuantity(quantity);
                }

                System.out.println("Enter new part price:");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    Epart.setPrice(price);
                }

                System.out.println("Enter new part warranty:");
                String warranty = scanner.nextLine();
                if (!warranty.isEmpty()) {
                    Epart.setWarranty(warranty);
                }

                System.out.println("Enter new part description:");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    Epart.setDescription(description);
                }

                System.out.println("Enter new Engine Type:");
                String engineType = scanner.nextLine();
                if (!engineType.isEmpty()) {
                    Epart.setEngineType(engineType);
                }

                System.out.println("Enter new part description:");
                String ESizeStr = scanner.nextLine();
                if (!ESizeStr.isEmpty()) {
                    double ESize = Double.parseDouble(ESizeStr);
                    Epart.setEngineSize(ESize);
                }

                // save updated car part object to database
                String updateQuery = "UPDATE Engine_parts SET part_number=?, name=?, manufacturer=?, supplier=?, quantity=?, price=?, warranty=?, description=?, engineType =?, engineSize =?  WHERE Intenal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setDouble(1, Epart.getPartNumber());
                updateStmt.setString(2, Epart.getName());
                updateStmt.setString(3, Epart.getManufacturer());
                updateStmt.setString(4, Epart.getSupplier());
                updateStmt.setInt(5, Epart.getQuantity());
                updateStmt.setDouble(6, Epart.getPrice());
                updateStmt.setString(7, Epart.getWarranty());
                updateStmt.setString(8, Epart.getDescription());
                updateStmt.setDouble(9, Epart.getPartNumber());
                updateStmt.setString(10, Epart.getEngineType());
                updateStmt.setDouble(11, Epart.getEngineSize());

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
}

