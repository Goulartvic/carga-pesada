package model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private List<Request> requests;

    public Customer(Address address, String cpf, String name, String password, String phoneNumber, int userId, String username) {
        super(address, cpf, name, password, phoneNumber, userId, username, UserType.CUSTOMER);
        requests = new ArrayList<>();
    }

    public Customer(Customer customer) {
        super(customer.getAddress(), customer.getCpf(), customer.getName(), customer.getPassword(), customer.getPhoneNumber(), customer.getUserId(), customer.getUsername(), customer.getUserType());
        requests = customer.getRequests();
    }

    public Customer() {
        this.requests = new ArrayList<>();
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }
}
