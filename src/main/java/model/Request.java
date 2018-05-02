package model;

public class Request {

    private Address arrivalDestination;

    private Address departure;

    private Customer customer;

    private Vehicle vehicle;

    private Worker worker;

    private Status status;

    public Request(Customer customer, Vehicle vehicle, Worker worker, Status status, Address arrivalDestination, Address departure) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.worker = worker;
        this.status = Status.ONHOLD;
        this.arrivalDestination = arrivalDestination;
        this.departure = departure;
    }
}
