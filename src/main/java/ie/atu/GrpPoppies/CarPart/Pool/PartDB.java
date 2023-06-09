package ie.atu.GrpPoppies.CarPart.Pool;

import java.util.Objects;
import java.util.Scanner;

public class PartDB {
    public static void PartMenu(){
        int choice = 0;
        while (choice != 5) {

            System.out.println("Car and Engine Parts Management System");
            System.out.println("1. Add Car Part");
            System.out.println("2. View All Parts");
            System.out.println("3. Delete Part");
            System.out.println("4. Update Part");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);
            choice = input.nextInt();

            switch (choice) {
                case 1-> addCarPart();
                case 2-> ReadTables.viewParts();
                case 3-> DeleteDB.deletePart();
                case 4-> updatePart();
                case 5-> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }

    }

    private static void updatePart() {
        Scanner scanner = new Scanner(System.in);

        String tableGet = ReadTables.listTables();
        System.out.println("Enter Internal ID to update a Part:");
        int Internal_ID = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        //change to switch when you are ready good lad :)
        if (Objects.equals(tableGet, "Car_Parts")) {//IDK does this work :)
            CarPartDB.updatePart(Internal_ID);
        } else if (Objects.equals(tableGet, "Engine_Parts")) {
            EnginePartDB.updatePart(Internal_ID);
        }else if(Objects.equals(tableGet, "Wheel_info")){
            TyreInfoDB.updatePart(Internal_ID);
        }
    }


    private static void addCarPart() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add a part to:");
        System.out.println("1. Car Part:");
        System.out.println("2. Engine Part:");
        System.out.println("3. Enter Wheel:");
        System.out.println("4. Back:");
        int choice = scanner.nextInt();


        while (choice != 4) {
            switch (choice) {
                case 1 -> System.out.println("Enter car part details:");
                case 2 -> System.out.println("Enter engine part details:");
                case 3 -> System.out.println("Enter Wheel details:");
            }
            System.out.println("Enter part number:");
            double partNumber = scanner.nextDouble();
            scanner.nextLine(); // issue going from double to string when taking values

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
            if (!warranty.isEmpty()) {
                warranty = "n/a";
            }

            System.out.println("Enter part description:");
            String description = scanner.nextLine();
            if (!description.isEmpty()) {
                description = "n/a";
            }

            if (choice == 1) {
                CarPartDB.savetoDatabase(description, warranty, price, supplier, manufacturer, name, partNumber, quantity);
            } else if (choice == 2) {//engine

                System.out.println("Enter Engine Type:");
                String engineType = scanner.nextLine();

                System.out.println("Enter Engine Size:");
                int engineSize = scanner.nextInt();

                EnginePartDB.savetoDatabase(description, warranty, price, supplier, manufacturer, name, partNumber, quantity, engineType, engineSize);
            } else if (choice == 3) {//tyre
                System.out.println("Enter Tyre Type:");
                String tyreType = scanner.nextLine();

                System.out.println("Enter tyre Size:");
                int tyreSize = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Enter Tyre Rating:");
                String tyreRating = scanner.nextLine();

                TyreInfoDB.savetoDatabase(description, warranty, price, supplier, manufacturer, name, partNumber, quantity, tyreSize, tyreRating, tyreType);
            }
            System.out.println("Part added:\n");

            System.out.println("Add a part to:");
            System.out.println("1. Car Part:");
            System.out.println("2. Engine Part:");
            System.out.println("3. Enter Wheel:");
            System.out.println("4. Back:");
            choice = scanner.nextInt();
        }
    }


}
