package ie.atu.GrpPoppies.CarPart;

public class CarPart {
        private double partNumber;
        private String name;
        private String manufacturer;
        private String supplier;
        private int quantity;
        private double price;
        private String warranty;
        private String description;

    public CarPart() {
    }

    public CarPart(double partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description) {
        this.partNumber = partNumber;
        this.name = name;
        this.manufacturer = manufacturer;
        this.supplier = supplier;
        this.quantity = quantity;
        this.price = price;
        this.warranty = warranty;
        this.description = description;
    }

    public double getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(double partNumber) {
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

