package ie.atu.GrpPoppies.CarPart;

public class EnginePart extends CarPart{
    private static String engineType;

    public EnginePart() {
    }

    public EnginePart(int partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description) {
        super(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
        this.engineType = engineType;
    }

    public static String getEngineType() {
        return engineType;
    }

    public static void setEngineType(String engineType) {
        EnginePart.engineType = engineType;
    }

    public static void saveToDatabase(){

    }
}
