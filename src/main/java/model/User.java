package model;

public abstract class User {

    private Address address;

    private String cpf;

    private String name;

    private String password;

    private String phoneNumber;

    private int userId;

    private String username;

    private UserType userType;

    public User() {
        this.address = new Address();
    }

    public User(Address address, String cpf, String name, String password, String phoneNumber, int userId, String username, UserType userType) {
        this.address = address;
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
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

    public void setUserType(int userType) {
        switch (userType) {
            case 1:
                this.userType = UserType.CUSTOMER;
                break;
            case 2:
                this.userType = UserType.WORKER;
                break;
        }
    }
}
