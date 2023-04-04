package ie.atu.GrpPoppies.atu.productv2;

import java.util.Scanner;

public class SoftwareApp {

    public static void main(String args[]) {
        // display a welcome message
        System.out.println("Welcome to the Software Viewer");
        System.out.println();

        // perform 1 or more selections
        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            System.out.print("Enter Software code: ");
            String softwareCode = sc.nextLine();  // read the Software code

            // get the Software object
            Software yourSoftware = SoftwareDB.getSoftware(softwareCode);

            // display the output
            System.out.println();
            if (yourSoftware != null) {
                System.out.println("Description: " + yourSoftware.toString());
                System.out.println("Price:       " + yourSoftware.getPriceFormatted());
            } else {
                System.out.println("No Software matches this Software code.");
            }

            System.out.println();
            System.out.println("Software count: " + Software.getCount() + "\n");

            // see if the user wants to continue
            System.out.print("Continue? (y/n): ");
            choice = sc.nextLine();
            System.out.println();
        }
        sc.close();
    }
}
