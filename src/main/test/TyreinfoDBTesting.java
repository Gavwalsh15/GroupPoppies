//import ie.atu.GrpPoppies.CarPart.Pool.TyreInfoDB;
import ie.atu.GrpPoppies.CarPart.TyreInfoDB;
//import org.jetbrains.annotations.Test;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TyreinfoDBTesting {

        TyreInfoDB myCalc;
        @Test
        public void testAdd()
        {
            myCalc = new TyreInfoDB();
            assertEquals(40, myCalc.add(20,20));


        }
}
