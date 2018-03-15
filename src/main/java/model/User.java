package model;

public abstract class User {

    private int userId;

    private String username;

    private String password;

    private String name;

    private UserType userType;

    public User(int userId, String username, String password, String name, UserType userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.userType = userType;
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
}
