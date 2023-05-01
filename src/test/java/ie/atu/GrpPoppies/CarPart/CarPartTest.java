package ie.atu.GrpPoppies.CarPart;

import ie.atu.GrpPoppies.CarPart.Pool.CarPartDB;
import ie.atu.GrpPoppies.CarPart.Pool.DatabaseUtils;
import ie.atu.GrpPoppies.CarPart.Standard.CarPart;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarPartTest {
    CarPart part;

    @Test
    public void testAddtoDB(){
        Connection conn = null;
        try {
            conn = DatabaseUtils.getConnection();


            part = new CarPart();
            String description = "Test part description";
            String warranty = "Test part warranty";
            double price = 10.99;
            String supplier = "Test supplier";
            String manufacturer = "Test manufacturer";
            String name = "Test part name";
            double partNumber = 123456789;
            int quantity = 1;

            CarPartDB.savetoDatabase(description, warranty, price, supplier, manufacturer, name, partNumber, quantity);

            String query = "SELECT * FROM Car_Parts WHERE part_number=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDouble(1, partNumber);
            ResultSet rs = stmt.executeQuery();
            assertEquals(price, rs.getInt("part_number"));
            assertEquals(name, rs.getString("name"));
            assertEquals(manufacturer, rs.getString("manufacturer"));
            assertEquals(supplier, rs.getString("supplier"));
            assertEquals(quantity, rs.getInt("quantity"));
            assertEquals(price, rs.getDouble("price"));
            assertEquals(warranty, rs.getString("warranty"));
            assertEquals(description, rs.getString("description"));
        }catch (SQLException e) {
            System.out.println(e);
        }
    }


}
