package control;

import model.*;

public class RequestController {

    private static RequestController instance = new RequestController();

    public RequestController() {
        super();
    }


    public void createRequest(String arrivalDestination, String departure, String vehiclePlate, int worker) {
//        TODO - FindVehicleByPlate(String vehiclePlate) criar no controlador do veiculo
//        TODO - FindUserByID(int user) criar no controlador do usuário

        Vehicle vehicle = new Vehicle("Ford", "F1000", "MXL-3155", VehicleSize.SMALL, false, 5.50);
        Address destination = new Address("Floripa", "SC", "Rua da destino", 200);
        Address depart = new Address("Floripa", "SC", "Rua da partida", 100);

        Worker userWorker = UserController.getInstance().findWorkerById(worker);

        Customer userCustomer = (Customer) UserController.getInstance().getSessionUser();

        Request request = new Request(userCustomer, vehicle, userWorker, destination, depart);

        VehicleController.getInstance().addRequestInVehicle(request, vehicle, userWorker);
    }

    public static RequestController getInstance() {
        return instance;
    }
}
