package ie.atu.GrpPoppies.CarPart.Standard;

public class CarOil {
    private String ViscGrade;
    private String OilClass;
    private String OilBase;
    private float Oillitres;
    private double price;

    public CarOil() {
    }

    public CarOil(String viscGrade, String oilClass, String oilBase, float oillitres, double price) {
        ViscGrade = viscGrade;
        OilClass = oilClass;
        OilBase = oilBase;
        Oillitres = oillitres;
        this.price = price;
    }

    public String getOilClass() {
        return OilClass;
    }

    public void setOilClass(String oilClass) {
        OilClass = oilClass;
    }

    public String getViscGrade() {return ViscGrade;}
    public void setViscGrade(String viscGrade) {ViscGrade = viscGrade;}

    public String getOilBase() {
        return OilBase;
    }

    public void setOilBase(String oilBase) {
        OilBase = oilBase;
    }

    public float getOillitres() {
        return Oillitres;
    }

    public void setOillitres(float oillitres) {
        Oillitres = oillitres;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
