package ie.atu.GrpPoppies.CarPart;

import java.util.Scanner;

public interface GarageApp {
    static void main(String[] args) {
        String viewParts;
        String addCarPart;
        String deleteCarPart;

        System.out.println("Poppies Garage");
        System.out.println("Add");
        System.out.println("delete");
        System.out.println("update");
        System.out.println("search");

        CarPartDB.addCarPart();
        CarPartDB.updateCarPart();
        CarPartDB.deleteCarPart();
    }
}

