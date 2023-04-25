package ie.atu.GrpPoppies.CarPart;

//done
public class Customer {
    private String CustomerName;
    private String email;
    private int number;

    public Customer() {
    }

    public Customer(String CustomerName, String email, int number) {
        this.CustomerName = CustomerName;
        this.email = email;
        this.number = number;
    }


    public String setCustomerName() {
        return CustomerName;
    }

    public String setEmail() {
        return email;
    }

    public int setNumber() {
        return number;
    }



    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
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


