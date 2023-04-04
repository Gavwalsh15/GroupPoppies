package ie.atu.GrpPoppies.atu.productv2;

public class SoftwareDB {

    public static Software getSoftware(String productCode) {
        // In a more realistic application, this code would
        // get the data for the product from a file or datamyBookase
        // For now, this code just uses if/else statements
        // to return the correct product data

      
            Software mySoftware = null;
            if (productCode.equalsIgnoreCase("studios")) {
                mySoftware = new Software();
                mySoftware.setCode(productCode);
                mySoftware.setDescription("Visual Studios");
                mySoftware.setPrice(57.50);
                mySoftware.setVersion("Microsoft 1.1");
            } else if (productCode.equalsIgnoreCase("eclipse")) {
                mySoftware = new Software();
                mySoftware.setCode(productCode);
                mySoftware.setDescription("Build Java apps");
                mySoftware.setPrice(57.50);
                mySoftware.setVersion("Eclipse Neon");
            } else if (productCode.equalsIgnoreCase("oracle")) {
                mySoftware = new Software();
                mySoftware.setCode(productCode);
                mySoftware.setDescription("Latest MySQL");
                mySoftware.setPrice(54.50);
                mySoftware.setVersion("Oracle 3.0");
            }
          
        return mySoftware;
    }
}
