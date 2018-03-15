package model;

public class Customer extends User {

    private String cpf;

    private String phoneNumber;

    private Address address;

    public Customer(int userId, String username, String password, String cpf, String name, String phoneNumber, Address address) {
        super(userId, username, password, name, UserType.CUSTOMER);
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
