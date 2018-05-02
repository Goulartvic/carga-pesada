package model;

public class Admin extends User {

    public Admin(int userId, String username, String password, String name, UserType userType, String cpf, String phoneNumber, Address address) {
        super(userId, username, password, name, UserType.ADMIN, cpf, phoneNumber, address);
    }
}
