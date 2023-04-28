package ie.atu.GrpPoppies.CarPart;

//done
public class Customer implements CustomerInterface {
    private String fname;       //changed back to private
    private String lname;
    private String email;
    private double phoneNum;

    public Customer(String fname, String lname, String email, double phoneNum) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneNum = phoneNum;
    }


    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public void setFname(String fname) {
        this.fname = fname;
    }

    @Override
    public String getLname() {

        return lname;
    }

    @Override
    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public double getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setPhoneNum(double phoneNum) {
        this.phoneNum = phoneNum;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum=" + phoneNum +
                '}';
    }
}


