package ie.atu.GrpPoppies.CarPartsV2;

public class Order {
    //  This  store the supplier's name and the availability status of the order
    private String supplier;
    private boolean availability;

    // When creating a new Order object, we need to provide the supplier's name and availability status
    public Order(String supplier, boolean availability) {
        // Store the provided information in our class attributes
        this.supplier = supplier;
        this.availability = availability;
    }

    // Now we'll add methods to get and set the values of our attributes

    // This method gives us the supplier's name
    public String getSupplier() {
        return supplier;
    }

    // With this method, we can change the supplier's name
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    // This method tells us if the order is available
    public boolean isAvailability() {
        return availability;
    }

    // With this method, we can update the availability status of the order
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}