package model;

import java.util.List;

public class Customer extends User {

    public Customer(Address address, String cpf, String name, String password, String phoneNumber, List<Request> requests, int userId, String username) {
        super(address, cpf, name, password, phoneNumber, requests, userId, username, UserType.CUSTOMER);
    }
}
