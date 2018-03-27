package model;

public class Request {

    private Customer customer;

    private Vehicle vehicle;

    private Worker worker;

    private Status status;

    public Request(Customer customer, Vehicle vehicle, Worker worker, Status status) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.worker = worker;
        this.status = Status.ONHOLD;
    }
}
