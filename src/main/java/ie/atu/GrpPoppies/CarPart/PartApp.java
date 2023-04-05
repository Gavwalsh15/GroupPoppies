package ie.atu.GrpPoppies.CarPart;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Scanner;
public class PartApp {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";

    public static void main(String[] args) {
        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, username, password);

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
                    case 1:
                        addCarPart();
                        break;
                    case 2:
                        viewAllParts();
                        break;
                    case 3:
                        //deletePart(conn);
                        break;
                    case 4:
                        System.out.println("Well done no Errors I hope!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }

            // Close the database connection
            //conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void viewAllParts() {
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM carpart");

            while (rs.next()) {
                    int partNumber = rs.getInt("part_number");
                    String name = rs.getString("name");
                    String manufacturer = rs.getString("manufacturer");
                    String supplier = rs.getString("supplier");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String warranty = rs.getString("warranty");
                    String description = rs.getString("description");
                    CarPart part = new CarPart(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
                    System.out.println(part.toString());
                }
            } catch(SQLException e){
                System.out.println("Error retrieving car parts: " + e.getMessage());
            }
    }

        private static void addCarPart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a part to:");
        System.out.println("1. Car Parts");
        System.out.println("2. Engine Parts");
        System.out.println("3. Wheel Info");
        System.out.println("4. Back");
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
                scanner.nextLine(); // consume newline character

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

                if(choice == 1){
                    CarPart part = new CarPart();
                    part.setPartNumber(partNumber);
                    part.setName(name);
                    part.setManufacturer(manufacturer);
                    part.setSupplier(supplier);
                    part.setQuantity(quantity);
                    part.setPrice(price);
                    part.setWarranty(warranty);
                    part.setDescription(description);
                }else if(choice == 2) {
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
                }else if(choice == 3) {
                    TyreInfo Tpart = new TyreInfo();
                    System.out.println("Enter Tyre Type:");
                    String tyreType = scanner.nextLine();

                    System.out.println("Enter tyre Size:");
                    int tyreSize = scanner.nextInt();

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
            System.out.println("1. Car Parts");
            System.out.println("2. Engine Parts");
            System.out.println("3. Wheel Info");
            System.out.println("4. Back");
            choice = scanner.nextInt();
            }
        }


    private static void savetoDatabase(CarPart part) {
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO carpart (part_number, name, manufacturer, supplier, quantity, price, warranty, " +
                    "description) VALUES (?,?,?,?,?,?,?,?)");

            // set the parameters for the PreparedStatement
            stmt.setDouble(1, part.getPartNumber());
            stmt.setString(2, part.getName());
            stmt.setString(3, part.getManufacturer());
            stmt.setString(4, part.getSupplier());
            stmt.setInt(5, part.getQuantity());
            stmt.setDouble(6, part.getPrice());
            stmt.setString(7, part.getWarranty());
            stmt.setString(8, part.getDescription());

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


    private static void savetoDatabase(EnginePart Epart) {
        try{
            // establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, password);
            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO engine_part (part_number, name, manufacturer, supplier, quantity, price, warranty, " +
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
}






