package ie.atu.GrpPoppies.CarPart;

import org.jetbrains.annotations.TestOnly;

public class CarPartDBTesting {
    CarPartDB myCalc;

    @TestOnly
    public void testAdd()
    {
        myCalc = new CarPartDB();
        AssertEquals(40, myCalc,add(20,20);
    }

    private Object add(int first, int second)
    {
        return (first + second);
    }
}
