package model;

public enum Status {
    ACCEPTED(1), ONHOLD(2), DENIED(3);

    private final int status;

    Status(int status) {
        this.status = status;
    }
}
