package ie.atu.GrpPoppies.CarPart;

import java.sql.Connection;
import java.sql.DriverManager;
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
            while (choice != 5) {
                System.out.println("Car and Engine Parts Management System");
                System.out.println("1. Add Car Part");
                System.out.println("2. Add Engine Part");
                System.out.println("3. View All Parts");
                System.out.println("4. Delete Part");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 1:
                        addCarPart();
                        break;
                    case 3:
                        //viewAllParts(conn);
                        break;
                    case 4:
                        //deletePart(conn);
                        break;
                    case 5:
                        System.out.println("Well done no Errors I hope!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                System.out.println();
            }

            // Close the database connection
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        private static void addCarPart(){
            System.out.println("What Table are you adding to?");
            Scanner scanner = new Scanner(System.in);
            CarPart part = new CarPart();

            System.out.println("Enter part number:");
            int partNumber = scanner.nextInt();
            part.setPartNumber(partNumber);

            System.out.println("Enter part name:");
            String name = scanner.nextLine();
            part.setName(name);

            System.out.println("Enter part manufacturer:");
            String manufacturer = scanner.nextLine();
            part.setManufacturer(manufacturer);

            System.out.println("Enter part supplier:");
            String supplier = scanner.nextLine();
            part.setSupplier(supplier);

            System.out.println("Enter part quantity:");
            int quantity = scanner.nextInt();
            part.setQuantity(quantity);

            System.out.println("Enter part price:");
            double price = scanner.nextDouble();
            part.setPrice(price);

            System.out.println("Enter part warranty:");
            String warranty = scanner.next();
            part.setWarranty(warranty);

            System.out.println("Enter part description:");
            String description = scanner.nextLine();
            part.setDescription(description);

            System.out.println("Part details:");
            System.out.println(part.toString());
        }
}
