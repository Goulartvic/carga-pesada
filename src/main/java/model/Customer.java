package model;

public class Customer extends User {

    public Customer(int userId, String username, String password, String name, UserType userType, String cpf, String phoneNumber, Address address) {
        super(userId, username, password, name, UserType.CUSTOMER, cpf, phoneNumber, address);
    }

}
