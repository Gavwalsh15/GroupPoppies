package ie.atu.GrpPoppies.CarPart;

public interface CustomerInterface {       //Basically adds abstraction meaning it doesn't care what it gets
    String toString();                     //So basically this method turns everything to String (i think)

    void setLname();
    void setFname();
    void setEmail();
    void setPhoneNum();

    String getFname();
    String getLname();
    String getEmail();
    double getPhoneNum();

    void setFname(String fname);
    void setLname(String lname);
    void setEmail(String email);
    void setPhoneNum(double number);


}

