package model;

public enum Status {
    ACCEPTED(1), ONHOLD(2), DENIED(3), COMPLETE(4), CANCELLED(5);

    private final int status;

    Status(int status) {
        this.status = status;
    }
}
