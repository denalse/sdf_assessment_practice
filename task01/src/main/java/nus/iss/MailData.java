package nus.iss;

public class MailData {
    
    private String firstName;
    private String lastName;
    private String address;
    private int years;

    public MailData(String firstName, String lastName, String address, int years) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.years = years;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }


    
}
