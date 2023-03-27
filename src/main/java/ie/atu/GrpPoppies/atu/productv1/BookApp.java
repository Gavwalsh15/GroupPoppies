package ie.atu.GrpPoppies.atu.productv1;

import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class BookApp {

    public static void main(String args[]) {
        // display a welcome message
        System.out.println("Welcome to the Book Viewer");
        System.out.println();

        // perform 1 or more selections
        Scanner sc = new Scanner(System.in);
        String choice = "y";
        while (choice.equalsIgnoreCase("y")) {
            System.out.print("Enter book code: ");
            String bookCode = sc.nextLine();  // read the book code

            Book yourBook = BookDB.getBook(bookCode);

            // display the output
            System.out.println();
            if (yourBook != null) {
                System.out.println("Description: " + yourBook.toString());
                System.out.println("Price:       "  + yourBook.getPriceFormatted());
            } else {
                System.out.println("No book matches this book code.");
            }

            System.out.println();
            System.out.println("Book count: " + Book.getCount() + "\n");

            System.out.print("Continue? (y/n): ");
            choice = sc.nextLine();
            System.out.println();
        }
        sc.close();
    }
}
