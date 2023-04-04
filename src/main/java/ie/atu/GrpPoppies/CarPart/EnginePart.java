package ie.atu.GrpPoppies.CarPart;

public class EnginePart extends CarPart{
    private static String engineType;
    private static double engineSize;

    public EnginePart() {
    }

    public EnginePart(double partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description) {
        super(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
        this.engineType = engineType;
        this.engineSize = engineSize;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        EnginePart.engineType = engineType;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        EnginePart.engineSize = engineSize;
    }
}
