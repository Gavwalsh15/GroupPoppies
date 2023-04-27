package ie.atu.GrpPoppies.CarPart;

//done
public class Customer {
    public String fname;
    public String lname;
    public String email;
    public double phoneNum;

    public Customer(String fname, String lname, String email, double phoneNum) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Customer() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(double phoneNum) {
        this.phoneNum = phoneNum;
    }


}


