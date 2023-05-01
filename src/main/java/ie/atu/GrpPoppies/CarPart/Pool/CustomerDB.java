package ie.atu.GrpPoppies.CarPart.Pool;

import ie.atu.GrpPoppies.CarPart.Standard.Customer;
import ie.atu.GrpPoppies.CarPart.Standard.CustomerInterface;

import java.sql.*;
import java.util.Scanner;
import static java.lang.Double.parseDouble;

public class CustomerDB {
    public static void CustomerMenu() {
        int choice = 0;

        while (choice != 5) {

            System.out.println("Customer Menu");
            System.out.println("1. Add Customer");
            System.out.println("2. View Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. Update Customer");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1-> addCustomer();
                case 2-> viewCustomers();
                case 3-> updateCustomer();
                case 4-> DeleteCustomer();
                case 5-> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
    private static void addCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add a Customer:");
        System.out.println("Enter Customer First Name:");
        String fname = scanner.nextLine();

        System.out.println("Enter Customer Last Name:");
        String lname = scanner.nextLine();

        System.out.println("Enter Customer Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Customer Number:");
        double phone = scanner.nextDouble();

        savetoDatabase(fname,lname,email,phone);
    }
    private static void savetoDatabase(String fname,String lname, String email, double number) {
        CustomerInterface CustomerDetail = new Customer(fname, lname, email, number);

        try {
            // establish a connection to the database
            Connection conn = DatabaseUtils.getConnection();
            String query = "INSERT INTO Customer (Fname, Lname, email, phoneNumber) VALUES (?, ?, ?, ?)";
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

    private static void updateCustomer() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Customer ID to update:");
            int customerID = scanner.nextInt();
            scanner.nextLine();

            Connection conn = DatabaseUtils.getConnection();

            String query = "SELECT * FROM Customer WHERE Internal_ID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // retrieve existing CustomerDetail object
                CustomerInterface CustomerDetail = new Customer(rs.getString("Fname"),
                        rs.getString("Lname"), rs.getString("Email"),
                        rs.getDouble("PhoneNumber"));       //Customer CustomerDetail = new Customer();



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
    private static void DeleteCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Internal ID to delete Part:");
        int Internal_ID = scanner.nextInt();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Customer WHERE Internal_ID = ?");
            stmt.setInt(1, Internal_ID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No Customer found with " + Internal_ID);
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting Customer: " + e.getMessage());
        }
    }

    private static void viewCustomers() {
        try {
            Connection conn = DatabaseUtils.getConnection();
            conn.createStatement();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Customer");

            System.out.println("How Would you Like To Filter");
            String col = ReadTables.listColumns("Customer");

            if(!col.equals("All")) {
                System.out.println("What are the customer details?");
                String details = new Scanner(System.in).nextLine();

                pstmt = conn.prepareStatement("SELECT * FROM Customer WHERE " + col + "=?");//overwrite if filter
                if (col.equals("Fname") || col.equals("Lname") || col.equals("Email")) {
                    pstmt.setString(1, details);
                } else if (col.equals("PhoneNumber")) {
                    double phone = Double.parseDouble(details);
                    pstmt.setDouble(1, phone);
                }
            }
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-30s", rsmd.getColumnName(i));//makes a cool looking table
            }
            System.out.println("\n");
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-30s", rs.getString(i));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving customer details: " + e.getMessage());
        }

    }
}


