package ie.atu.GrpPoppies.CarPart;

import ie.atu.GrpPoppies.CarPart.Pool.*;
import java.util.Scanner;
import static ie.atu.GrpPoppies.CarPart.Pool.CustomerDB.CustomerMenu;

public class PartApp {
    public static void main(String[] args) {
        try {
            // Display the menu
            int choice = 0;
            while (choice != 4) {

                System.out.println("Car and Engine Parts Management System");
                System.out.println("1. Add Part Menu");
                System.out.println("2. Oil Menu");
                System.out.println("3. Customer Menu");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                Scanner input = new Scanner(System.in);
                choice = input.nextInt();

                switch (choice) {
                    case 1 -> PartDB.PartMenu();
                    case 2 -> CarOilDB.OilMenu();
                    case 3 -> CustomerMenu();
                    case 4 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }

                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




