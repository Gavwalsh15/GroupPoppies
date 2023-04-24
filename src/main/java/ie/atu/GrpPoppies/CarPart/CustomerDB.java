package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CustomerDB {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";
    public static void savetoDatabase(String CustomerName, String email, int number) {
        Customer CustomerDetail = new Customer();

        CustomerDetail.setCustomerName(CustomerName);
        CustomerDetail.setEmail(email);
        CustomerDetail.setNumber(number);

        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // get column names from the carpart table
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "Customer", null);

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
            String query = "INSERT INTO Customer (" + columnGet + ") VALUES (" + columnValues + ")";
            PreparedStatement stmt = conn.prepareStatement(query);
            // set the parameters for the PreparedStatement
            CustomerDetail.setCustomerName(CustomerName);
            CustomerDetail.setEmail(email);
            CustomerDetail.setNumber(number);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Customer saved to database.");
            } else {
                System.out.println("Failed to save Customer to database.");
            }

            // close the connection to the database
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error saving Customer to database: " + e.getMessage());
        }
        return;
    }

    public static void updateCustomer(int partNumber) {
        try {
            Scanner scanner = new Scanner(System.in);

            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM Car_Parts WHERE part_number=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, partNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing CustomerDetail object
                Customer CustomerDetail = new Customer();
                CustomerDetail.setCustomerName(rs.getString("customer"));
                CustomerDetail.setEmail(rs.getString("email"));
                CustomerDetail.setNumber(rs.getInt("number"));


                // update fields of car part object
                System.out.println("Enter Customer:");
                String name = scanner.nextLine();
                if (!name.isEmpty()) {
                    CustomerDetail.setCustomerName(name);
                }

                System.out.println("Enter new part manufacturer:");
                String manufacturer = scanner.nextLine();
                if (!manufacturer.isEmpty()) {
                    CustomerDetail.setEmail(manufacturer);
                }


                System.out.println("Enter new number:");
                String quantityStr = scanner.nextLine();
                if (!quantityStr.isEmpty()) {
                    int quantity = parseInt(quantityStr);
                    CustomerDetail.setNumber(quantity);
                }

                // save updated car part object to database
                String updateQuery = "UPDATE Customer SET details=?, name=?, manufacturer=?, supplier=?, quantity=?, price=?, warranty=?, description=? WHERE Intenal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, CustomerDetail.getCustomerName());
                updateStmt.setString(2, CustomerDetail.getEmail());
                updateStmt.setInt(3, CustomerDetail.getNumber());
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


