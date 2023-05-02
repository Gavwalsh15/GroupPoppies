package java;

import ie.atu.GrpPoppies.CarPart.CustomerDB;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CustomerDBTesting {
    public class CustomerDBTesting {

        public class Customer {
            CustomerDB myCalc;

            @Test
            public void testAdd()
            {
                myCalc = new CustomerDB();
                assertEquals(40, myCalc.add(20,20));

            }
        }
    }
}
