
// Customer class erved to preserve and return info about a customer
public class Customer {
    String fName;
    String lName;
    String SSN;
    String DOB;

    public Customer(String fName, String lName, String SSN, String DOB) {
        this.fName = fName;
        this.lName = lName;
        this.SSN = SSN;
        this.DOB = DOB;
    }

    public String getFullName() {
        return fName + " " + lName;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public String getSSN() {
        return SSN;
    }

    public String getDOB() {
        return DOB;
    }
}