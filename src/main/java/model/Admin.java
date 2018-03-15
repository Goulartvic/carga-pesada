package model;

public class Admin extends User {

    public Admin(int userId, String username, String password, String name) {
        super(userId, username, password, name, UserType.ADMIN);
    }
}
