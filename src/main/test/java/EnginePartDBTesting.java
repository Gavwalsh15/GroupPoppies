package java;

import ie.atu.GrpPoppies.CarPart.EnginePartDB;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class EnginePartDBTesting {

    public class Engine {
        EnginePartDB myCalc;

        @Test
        public void testAdd()
        {
            myCalc = new EnginePartDB();
            assertEquals(40, myCalc.add(20,20));

        }
    }
}
