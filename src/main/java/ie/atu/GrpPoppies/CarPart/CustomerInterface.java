package ie.atu.GrpPoppies.CarPart;

public interface CustomerInterface {       //Basically adds abstraction meaning it doesn't care what it gets
    String toString();         //So basically this method turns everything to String (i think)

    void setlname();
    void setfname();
    void setemail();
    void setphoneNum();

    void setLname();
    void setFname();
    void setEmail();
    void setPhoneNum();

    String getFname();

    String getLname();

    String getEmail();

    double getPhoneNum();

    void setEmail(String email);

    void setPhoneNum(double number);

    void setFname(String lname);

    void setLname(String lname);
}

