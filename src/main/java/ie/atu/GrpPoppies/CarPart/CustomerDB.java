package ie.atu.GrpPoppies.CarPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class CustomerDB {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";

    public static void savetoDatabase(String fname,String lname, String email, double number) {
        CustomerInterface CustomerDetail = new Customer(fname, lname, email, number);

        //CustomerDetail.setFname(fname);       not relevant anymore
        //CustomerDetail.setLname(lname);
        //CustomerDetail.setEmail(email);
        //CustomerDetail.setPhoneNum(number);

        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // get column names from the Customer table
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
            stmt.setString(1, CustomerDetail.getFname());
            stmt.setString(2, CustomerDetail.getLname());
            stmt.setString(3, CustomerDetail.getEmail());
            stmt.setDouble(4, CustomerDetail.getPhoneNum());

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
    }

    public static void updateCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Customer ID to update:");
            int customerID = scanner.nextInt();

            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM Customer WHERE Internal_ID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing CustomerDetail object
                CustomerInterface CustomerDetail = new Customer(rs.getString("Fname"), rs.getString("Lname"), rs.getString("Email"), rs.getDouble("PhoneNumber"));       //Customer CustomerDetail = new Customer();

                //CustomerDetail.setFname(rs.getString("Fname"));     not relevant anymore
                //CustomerDetail.setLname(rs.getString("Lname"));
                //CustomerDetail.setEmail(rs.getString("email"));
                //CustomerDetail.setPhoneNum(rs.getDouble("number"));


                // update fields of Customer object
                System.out.println("Enter Customer First Name:");
                String Fname = scanner.nextLine();
                if (!Fname.isEmpty()) {
                    CustomerDetail.setFname(Fname);
                }

                System.out.println("Enter Customer Last Name:");
                String Lname = scanner.nextLine();
                if (!Lname.isEmpty()) {
                    CustomerDetail.setLname(Lname);
                }

                System.out.println("Enter new email:");
                String email = scanner.nextLine();
                if (!email.isEmpty()) {
                    CustomerDetail.setEmail(email);
                }


                System.out.println("Enter new number:");
                String numberStr = scanner.nextLine();
                if (!numberStr.isEmpty()) {
                    double number = parseDouble(numberStr);
                    CustomerDetail.setPhoneNum(number);
                }

                // save updated car part object to database
                String updateQuery = "UPDATE Customer SET Fname=?, Lname=?, Email=?, PhoneNumber=? WHERE Internal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, CustomerDetail.getFname());
                updateStmt.setString(2, CustomerDetail.getLname());
                updateStmt.setString(3, CustomerDetail.getEmail());
                updateStmt.setDouble(4, CustomerDetail.getPhoneNum());
                updateStmt.setDouble(5, customerID);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer updated successfully.");
                } else {
                    System.out.println("Failed to update car part.");
                }
            } else {
                System.out.println("Customer not found.");
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error Updating Customer: " + e.getMessage());
        }
    }
}


