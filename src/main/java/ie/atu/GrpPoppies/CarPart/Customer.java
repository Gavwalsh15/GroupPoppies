package ie.atu.GrpPoppies.CarPart;

//done
public class Customer {
    private String CustomerName;
    private String email;
    private int number;

    public Customer(String CustName, String email, int number) {
        this.CustomerName = CustName;
        this.email = email;
        this.number = number;
    }

    public String getCustName() {
        return CustomerName;
    }

    public void setCustName(String name) {
        this.CustomerName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}


