package ie.atu.GrpPoppies.CarPart;

public class TyreInfo extends CarPart{
    private String TyreType;
    private String TyreRating;
    private double WheelSize;

    public TyreInfo() {
    }

    public TyreInfo(double partNumber, String name, String manufacturer, String supplier, int quantity, double price, String warranty, String description, String TyreType, String TyreRating, int WheelSize) {
        super(partNumber, name, manufacturer, supplier, quantity, price, warranty, description);
        this.TyreType = TyreType;
        this.TyreRating = TyreRating;
        this.WheelSize = WheelSize;
    }

    public String getTyreType() {
        return TyreType;
    }

    public void setTyreType(String tyreType) {
        TyreType = tyreType;
    }

    public String getTyreRating() {
        return TyreRating;
    }

    public void setTyreRating(String tyreRating) {
        TyreRating = tyreRating;
    }

    public double getWheelSize() {
        return WheelSize;
    }

    public void setWheelSize(double wheelSize) {
        WheelSize = wheelSize;
    }
}
