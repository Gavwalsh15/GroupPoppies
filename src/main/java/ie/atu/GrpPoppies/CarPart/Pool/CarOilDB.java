package ie.atu.GrpPoppies.CarPart.Pool;

import ie.atu.GrpPoppies.CarPart.Standard.CarOil;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CarOilDB {
    public static void OilMenu() {
        int choice = 0;

        while (choice != 4) {

            System.out.println("Oil Menu");
            System.out.println("1. Add Oils");
            System.out.println("2. View Oils");
            System.out.println("3. Edit Oil Amounts");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1-> newOil();
                case 2-> viewOils();
                case 3-> addOils();
                case 4-> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private static void addOils() {
        float lAdded = 0;
        double cost = 0;
        float tcost;

        Scanner scanner = new Scanner(System.in);
        viewOils();
        System.out.println("What Oil are you changing");
        int Internal_ID = scanner.nextInt();
        scanner.nextLine();

        try {
            Connection conn = DatabaseUtils.getConnection();

            String query = "SELECT * FROM CarOils WHERE Internal_ID=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Internal_ID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CarOil carOil = new CarOil();

                carOil.setViscGrade(rs.getString("ViscosityGrade"));
                carOil.setOilClass(rs.getString("Classification"));
                carOil.setOilBase(rs.getString("BaseOilType"));
                carOil.setOillitres(rs.getFloat("NumberofL"));
                carOil.setPrice(rs.getDouble("Price"));

            try {
                while (true) {
                    System.out.println("1. Add Oil\n2. Used Oil");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1) {
                        System.out.println("How much was bought:");
                        lAdded = scanner.nextFloat();
                        scanner.nextLine();
                        System.out.println("Cost Per Liter");
                        cost = scanner.nextFloat();
                        scanner.nextLine();
                        tcost = (float) (cost * lAdded);
                        System.out.println("Total Cost : " + String.format("%.2f", tcost));

                        float diff = (float) (cost - carOil.getPrice());
                        System.out.println("Difference of " + diff + " from last order.");
                        lAdded = lAdded + carOil.getOillitres();

                    } else if (choice == 2) {
                        float oilremain;
                        System.out.println("How much was used:");
                        lAdded = scanner.nextInt();
                        oilremain = carOil.getOillitres() - lAdded;
                        if (oilremain <= 0) {
                            System.out.println("Error amount is less than 0");
                            lAdded = carOil.getOillitres();
                        } else {
                            tcost = (float)(lAdded * carOil.getPrice());
                            System.out.println("Cost of Oil Used: " + String.format("%.2f", tcost));
                            oilremain = carOil.getOillitres() - lAdded;
                            System.out.println(oilremain);
                        }
                        if (oilremain < 50) {
                            System.out.println("WARNING OIL RESERVE LOW!");
                        }
                        cost = carOil.getPrice(); //for last price entered
                    }

                    if(choice == 1 || choice == 2){break;}
                        else{
                        System.out.println("1 or 2");
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Error Please Enter Correct Value: " + e);
            }

                String updateQuery = "UPDATE CarOils SET NumberofL=?, Price=? WHERE Internal_ID=?";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setFloat(1, lAdded);
                updateStmt.setDouble(2, cost);
                updateStmt.setInt(3, Internal_ID);


                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Oil updated successfully.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error Updating Oil: " + e.getMessage());
        }
    }


    public static void viewOils() {
        try {
            Connection conn = DatabaseUtils.getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CarOils");

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.printf("%-20s", rsmd.getColumnName(i));
            }
            System.out.println("\n");
            while (rs.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    System.out.printf("%-20s", rs.getString(i));
                }
                System.out.println();
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("Error retrieving Oils: " + e.getMessage());
        }
    }

    public static void newOil() {
        System.out.println("New Oil");
        CarOil caroil = new CarOil();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Viscosity Grade:");
        String ViscosityGrade = scanner.nextLine();
        caroil.setViscGrade(ViscosityGrade);

        System.out.println("Enter Oil Class:");
        String Class = scanner.nextLine();
        caroil.setOilClass(Class);

        System.out.println("Enter Oil Base:");
        String Oilbase = scanner.nextLine();
        caroil.setOilBase(Oilbase);

        System.out.println("Enter Oil Amount:");
        float amountL = scanner.nextFloat();
        caroil.setOillitres(amountL);

        System.out.println("Enter Oil Price (Euro/L):");
        double price = scanner.nextDouble();
        caroil.setPrice(price);

        try {
            Connection conn = DatabaseUtils.getConnection();

            String query = "INSERT INTO CarOils (ViscosityGrade, Classification , BaseOilType, NumberofL, price) VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            // set the parameters for the PreparedStatement
            stmt.setString(1, caroil.getViscGrade());
            stmt.setString(2, caroil.getOilClass());
            stmt.setString(3, caroil.getOilBase());
            stmt.setFloat(4, caroil.getOillitres());
            stmt.setDouble(5, caroil.getPrice());

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
