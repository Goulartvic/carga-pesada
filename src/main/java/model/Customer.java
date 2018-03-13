package model;

public class Customer extends User {

    private String cpf;

    private String phoneNumber;

    private Address address;

    public Customer(String username, String password, String cpf, String name, String phoneNumber, Address address) {
        super(username, password, name);
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
