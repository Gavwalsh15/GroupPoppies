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
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Connect to the database
            //Connection conn = DriverManager.getConnection(url, username, password);

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
                        //viewAllParts(conn);
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

    private static void addCarPart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a part to:");
        System.out.println("1. Car Parts");
        System.out.println("2. Engine Parts");
        System.out.println("3. Exit");
        int choice = scanner.nextInt();
        CarPart part = new CarPart();
        EnginePart Epart = new EnginePart();
        while (choice != 3) {
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter car part details:");
                }
                case 2 -> {

                    System.out.println("Enter engine part details:");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

                System.out.println("Enter part number:");
                double partNumber = scanner.nextDouble();

                System.out.println("Enter part name:");
                String name = scanner.nextLine();

                System.out.println("Enter part manufacturer:");
                String manufacturer = scanner.nextLine();

                System.out.println("Enter part supplier:");
                scanner.nextLine();
                String supplier = scanner.nextLine();

                System.out.println("Enter part quantity:");
                int quantity = scanner.nextInt();

                System.out.println("Enter part price:");
                double price = scanner.nextDouble();

                System.out.println("Enter part warranty:");
                String warranty = scanner.nextLine();

                System.out.println("Enter part description:");
                scanner.nextLine();
                String description = scanner.nextLine();

                if(choice == 1){
                    part.setPartNumber(partNumber);
                    part.setName(name);
                    part.setManufacturer(manufacturer);
                    part.setSupplier(supplier);
                    part.setQuantity(quantity);
                    part.setPrice(price);
                    part.setWarranty(warranty);
                    part.setDescription(description);
                } else if(choice == 2) {
                    System.out.println("Enter Engine Type:");
                    scanner.nextLine();
                    String engineType = scanner.nextLine();
                    Epart.setEngineType(engineType);

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
                }

                System.out.println("Part added:");
                System.out.println(part.toString());

            }
            savetoDatabase(part);
            System.out.println("Add a part to:");
            System.out.println("1. Car Parts");
            System.out.println("2. Engine Parts");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
        }


    private static void savetoDatabase(CarPart part) {
        try {
            // establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts;user=CloudSAe622a702@carpartserver;password=GroupPoppies2023;");

            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO car_parts (part_number, name, manufacturer, supplier, quantity, price, warranty, description) VALUES (?,?,?,?,?,?,?,?)");

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
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts;user=CloudSAe622a702@carpartserver;password=GroupPoppies2023;");

            // create a PreparedStatement to insert the CarPart data into the database
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO engine_parts (part_number, name, manufacturer, supplier, quantity, price, warranty, description, engine_size, engine_type) VALUES (?,?,?,?,?,?,?,?,?,?)");

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
}



