package ie.atu.GrpPoppies.CarPart;public class
// This is our Car class
public class Car {
    // We're going to store the car's registration number here as a String
    private String registrationNumber;

    // When we create a new Car object, we'll need to provide a registration number
    public Car(String registrationNumber) {
        // We'll store the provided registration number in our class attribute
        this.registrationNumber = registrationNumber;
    }

    // If we want to know the registration number of a car, we'll use this method
    public String getRegistrationNumber() {
        // This method returns the car's registration number
        return registrationNumber;
    }

    // If we need to change the car's registration number, we'll use this method
    public void setRegistrationNumber(String registrationNumber) {
        // This method updates the car's registration number with the new one provided
        this.registrationNumber = registrationNumber;
    }
}