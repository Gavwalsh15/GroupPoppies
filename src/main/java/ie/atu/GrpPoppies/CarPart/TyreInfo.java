package ie.atu.GrpPoppies.CarPart;

public class TyreInfo extends CarPart{
    private static String TyreType;
    private static String TyreRating;
    private static double WheelSize;

    public TyreInfo() {
    }

    public TyreInfo(double partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description) {
        super(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
        this.TyreType = TyreType;
        this.TyreRating = TyreRating;
        this.WheelSize = WheelSize;
    }

    public static String getTyreType() {
        return TyreType;
    }

    public static void setTyreType(String tyreType) {
        TyreType = tyreType;
    }

    public static String getTyreRating() {
        return TyreRating;
    }

    public static void setTyreRating(String tyreRating) {
        TyreRating = tyreRating;
    }

    public static double getWheelSize() {
        return WheelSize;
    }

    public static void setWheelSize(double wheelSize) {
        WheelSize = wheelSize;
    }
}
