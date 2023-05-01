package ie.atu.GrpPoppies.CarPart;

public class EnginePart extends CarPart{
    private String engineType;
    private double engineSize;

    public EnginePart() {
    }

    public EnginePart(double partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description, String engineType , double engineSize) {
        super(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
        this.engineType = engineType;
        this.engineSize = engineSize;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }
}
