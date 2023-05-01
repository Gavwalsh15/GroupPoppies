package ie.atu.GrpPoppies.CarPart.Pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteDB {

    public static void deletePart() {
        Scanner scanner = new Scanner(System.in);

        String tableGet = ReadTables.listTables();
        System.out.println("Enter Internal ID to delete Part:");
        int Internal_ID = scanner.nextInt();
        try {
            Connection conn = DatabaseUtils.getConnection();
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + tableGet + " WHERE Internal_ID = ?");
            stmt.setInt(1, Internal_ID);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No part found with part number " + Internal_ID);
            } else {
                System.out.println(rowsAffected + " row(s) deleted.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting part: " + e.getMessage());
        }
    }
}
