package model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Address address;

    private String cpf;

    private String name;

    private String password;

    private String phoneNumber;

    private List<Request> requests;

    private int userId;

    private String username;

    private UserType userType;

    public User() {}

    public User(Address address, String cpf, String name, String password, String phoneNumber, List<Request> requests, int userId, String username, UserType userType) {
        this.address = address;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.requests = new ArrayList<>();
        this.userId = userId;
        this.username = username;
        this.userType = userType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
