package ie.atu.GrpPoppies.CarPart;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.Scanner;

        import static java.lang.Integer.parseInt;

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


    public static void updatePart(int Internal_ID) {
        try {
            Scanner scanner = new Scanner(System.in);

            Connection conn = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM Wheel_info WHERE Internal_ID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Internal_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing car part object by using database
                TyreInfo Tpart = new TyreInfo();
                Tpart.setPartNumber(rs.getInt("part_number"));
                Tpart.setName(rs.getString("name"));
                Tpart.setManufacturer(rs.getString("manufacturer"));
                Tpart.setSupplier(rs.getString("supplier"));
                Tpart.setQuantity(rs.getInt("quantity"));
                Tpart.setPrice(rs.getDouble("price"));
                Tpart.setWarranty(rs.getString("warranty"));
                Tpart.setDescription(rs.getString("description"));

                // update fields of car part object
                System.out.println("Enter new part Part Number:");
                String PartNumStr = scanner.nextLine();
                if (!PartNumStr.isEmpty()) {
                    int PartNum = parseInt(PartNumStr);
                    Tpart.setPartNumber(PartNum);
                }

                System.out.println("Enter new Tyre name:");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    Tpart.setName(name);
                }

                System.out.println("Enter new Tyre manufacturer:");
                String manufacturer = scanner.nextLine();
                if (!manufacturer.isEmpty()) {
                    Tpart.setManufacturer(manufacturer);
                }

                System.out.println("Enter new Tyre supplier:");
                String supplier = scanner.nextLine();
                if (!supplier.isEmpty()) {
                    Tpart.setSupplier(supplier);
                }

                System.out.println("Enter new Tyre quantity:");
                String quantityStr = scanner.nextLine();
                if (!quantityStr.isEmpty()) {
                    int quantity = parseInt(quantityStr);
                    Tpart.setQuantity(quantity);
                }

                System.out.println("Enter new Tyre price:");
                String priceStr = scanner.nextLine();
                if (!priceStr.isEmpty()) {
                    double price = Double.parseDouble(priceStr);
                    Tpart.setPrice(price);
                }

                System.out.println("Enter new Tyre warranty:");
                String warranty = scanner.nextLine();
                if (!warranty.isEmpty()) {
                    Tpart.setWarranty(warranty);
                }

                System.out.println("Enter new Tyre description:");
                String description = scanner.nextLine();
                if (!description.isEmpty()) {
                    Tpart.setDescription(description);
                }

                System.out.println("Enter new Tyre Type:");
                String tyreRating = scanner.nextLine();
                if (!tyreRating.isEmpty()) {
                    Tpart.setTyreRating(tyreRating);
                }

                System.out.println("Enter new Wheel Size:");
                String wheelSizeStr = scanner.nextLine();
                if (!wheelSizeStr.isEmpty()) {
                    int wheelSize = parseInt(wheelSizeStr);
                    Tpart.setWheelSize(wheelSize);
                }

                // save updated car part object to database
                String updateQuery = "UPDATE Wheel_info SET part_number=?, name=?, manufacturer=?, supplier=?, quantity=?, price=?, warranty=?, description=?, tyretype =? , tyrerating =?, wheelsize =? WHERE Internal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);

                updateStmt.setDouble(1, Tpart.getPartNumber());
                updateStmt.setString(2, Tpart.getName());
                updateStmt.setString(3, Tpart.getManufacturer());
                updateStmt.setString(4, Tpart.getSupplier());
                updateStmt.setInt(5, Tpart.getQuantity());
                updateStmt.setDouble(6, Tpart.getPrice());
                updateStmt.setString(7, Tpart.getWarranty());
                updateStmt.setString(8, Tpart.getDescription());
                updateStmt.setString(9, Tpart.getTyreType());
                updateStmt.setString(10, Tpart.getTyreRating());
                updateStmt.setDouble(11, Tpart.getWheelSize());
                updateStmt.setInt(12, Internal_ID);



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