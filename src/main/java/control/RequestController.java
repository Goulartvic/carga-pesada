package control;

import model.*;

public class RequestController {

    private static RequestController instance = new RequestController();

    public RequestController() {
        super();
    }


    public void createRequest(String arrivalDestination, String departure, Vehicle vehicle, Worker worker) {
//        TODO - FindVehicleByPlate(String vehiclePlate) criar no controlador do veiculo
//        TODO - FindUserByID(int user) criar no controlador do usuário

        Address destination = new Address("Floripa", "SC", "Rua da destino", 200);
        Address depart = new Address("Floripa", "SC", "Rua da partida", 100);

        Customer userCustomer = (Customer) UserController.getInstance().getSessionUser();

        Request request = new Request(userCustomer, vehicle, worker, destination, depart);

        VehicleController.getInstance().addRequestInVehicle(request, vehicle, worker);
    }

    public static RequestController getInstance() {
        return instance;
    }
}
