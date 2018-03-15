package model;

public enum UserType {
    CUSTOMER(1), WORKER(2), ADMIN(3);

    private final int userType;

    UserType(int userType) {
        this.userType = userType;
    }

    public int getUserType() {
        return userType;
    }
}
