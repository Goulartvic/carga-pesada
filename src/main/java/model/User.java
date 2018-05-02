package model;

public class User {

    private int userId;

    private String username;

    private String password;

    private String name;

    private String cpf;

    private String phoneNumber;

    private Address address;

    private UserType userType;

    public User(int userId, String username, String password, String name, UserType userType, String cpf, String phoneNumber, Address address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.cpf = cpf;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
