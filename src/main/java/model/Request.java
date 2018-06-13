package model;

public class Request {

    private Address arrivalDestination;

    private Address departure;

    private Customer customer;

    private Vehicle vehicle;

    private Worker worker;

    private Status status;

    public Request() {
    }

    public Request(Customer customer, Vehicle vehicle, Worker worker, Address arrivalDestination, Address departure) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.worker = worker;
        this.status = Status.ONHOLD;
        this.arrivalDestination = arrivalDestination;
        this.departure = departure;
    }

    public Address getArrivalDestination() {
        return arrivalDestination;
    }

    public void setArrivalDestination(Address arrivalDestination) {
        this.arrivalDestination = arrivalDestination;
    }

    public Address getDeparture() {
        return departure;
    }

    public void setDeparture(Address departure) {
        this.departure = departure;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int status) {
        switch (status) {
            case 1:
                this.status = Status.ACCEPTED;
                break;
            case 2:
                this.status = Status.ONHOLD;
                break;
            case 3:
                this.status = Status.DENIED;
                break;
            case 4:
                this.status = Status.COMPLETE;
                break;
            case 5:
                this.status = Status.CANCELLED;
                break;
        }
    }
}
