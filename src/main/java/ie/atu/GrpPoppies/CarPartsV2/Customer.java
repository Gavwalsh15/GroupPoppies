
package ie.atu.GrpPoppies.CarPartsV2;

// The Customer class represents a customer of the CarPartsV2 store
public class Customer {
    // The name of the customer
    private String name;
    // The email address of the customer
    private String email;
    // The phone number of the customer
    private String phoneNumber;

    // Constructor that initializes the Customer object with a name, email, and phone number
    public Customer(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Default constructor for the Customer class
    public Customer() {

    }

    // Returns the name of the customer
    public String getName() {
        return name;
    }

    // Sets the name of the customer
    public void setName(String name) {
        this.name = name;
    }

    // Returns the email address of the customer
    public String getEmail() {
        return email;
    }

    // Sets the email address of the customer
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns the phone number of the customer
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Sets the phone number of the customer
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
