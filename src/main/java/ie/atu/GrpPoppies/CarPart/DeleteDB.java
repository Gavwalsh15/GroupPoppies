package ie.atu.GrpPoppies.CarPart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteDB {

    public static void deletePart() {
        Scanner scanner = new Scanner(System.in);

        String tableGet = ReadTables.listTables();
        System.out.println("Enter part number to delete:");
        int partNumber = scanner.nextInt();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + tableGet + " WHERE part_number = ?");
            stmt.setInt(1, partNumber);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No part found with part number " + partNumber);
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting part: " + e.getMessage());
        }
    }

    /*
    public static void deleteCustomers() {
        Scanner scanner = new Scanner(System.in);

        String tableGetCustomer = ReadTablesCustomer.listTablesC();
        System.out.println("Enter Customer ID:");
        int CustomerNumber = scanner.nextInt();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + tableGetCustomer + " WHERE CustomerNumber = ?");
            stmt.setInt(1, CustomerNumber);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No Customer with that number " + CustomerNumber);
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting Customer: " + e.getMessage());
        }
    }
    */
}
