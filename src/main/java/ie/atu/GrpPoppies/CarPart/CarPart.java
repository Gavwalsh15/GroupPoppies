package ie.atu.GrpPoppies.CarPart;

public class CarPart {
        private static int partNumber;
        private static String name;
        private static String manufacturer;
        private static String supplier;
        private static int quantity;
        private static double price;
        private static String warranty;
        private static String description;

    public CarPart() {
    }

    public CarPart(int partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description) {
        this.partNumber = partNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}