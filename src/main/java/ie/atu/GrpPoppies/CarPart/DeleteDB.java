package ie.atu.GrpPoppies.CarPart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteDB {
    static String url = "jdbc:sqlserver://carpartserver.database.windows.net:1433;database=CarParts";
    static String username = "CloudSAe622a702@carpartserver";
    static String password = "GroupPoppies2023";
    public static void deletePart() {
        Scanner scanner = new Scanner(System.in);

        String tableGet = ReadTables.listTables();
        System.out.println("Enter part number to delete:");
        int partNumber = scanner.nextInt();
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
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
}
