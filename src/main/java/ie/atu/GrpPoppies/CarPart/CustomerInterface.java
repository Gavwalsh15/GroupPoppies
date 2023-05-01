package ie.atu.GrpPoppies.CarPart;

public interface CustomerInterface {       //Basically adds abstraction meaning it doesn't care what it gets
    //String toString();                     //So basically this method turns everything to String (I think)
    String getFname();                      //methods that return info
    String getLname();
    String getEmail();
    double getPhoneNum();
    void setFname(String fname);            //same thing but for setting only
    void setLname(String lname);
    void setEmail(String email);
    void setPhoneNum(double number);
}

