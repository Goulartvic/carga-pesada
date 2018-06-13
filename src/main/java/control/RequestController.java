package control;

import model.*;

public class RequestController {

    private static RequestController instance = new RequestController();

    public RequestController() {
        super();
    }


    public void createRequest(String arrivalDestination, String departure, String vehiclePlate, int custumer, int worker) {
//        TODO - FindVehicleByPlate(String vehiclePlate) criar no controlador do veiculo
//        TODO - FindUserByID(int user) criar no controlador do usuário

        Vehicle vehicle = new Vehicle("Ford", "F1000", "MXL-3155", VehicleSize.SMALL, false, 5.50);
        Address address = new Address("Floripa", "SC", "Rua da rua", 110);
        Address destination = new Address("Floripa", "SC", "Rua da destino", 200);
        Address depart = new Address("Floripa", "SC", "Rua da partida", 100);
        Worker userWorker = new Worker(address, "100.100.100-10", "Jose", "123", "9999-9999", 1, "josegameplays");
        Customer userCustomer = new Customer(address, "200.200.200-20", "Maria", "123", "8888-9999", 2, "mariazinha");


        Request request = new Request(userCustomer, vehicle, userWorker, destination, depart);
    }

}
