package control;

import model.*;

public class RequestController {

    private static RequestController instance = new RequestController();

    public RequestController() {
        super();
    }


    public void createRequest(String departureStreet, String departureNumber, String departureCity, String departureState,
                              String destinationStreet, String destinationNumber, String destinationCity, String destinationState, Vehicle vehicle, Worker workerSelected) {

        Address destination = new Address(destinationCity, destinationState, destinationStreet, Integer.parseInt(destinationNumber));
        Address departure = new Address(departureCity, departureState, departureStreet, Integer.parseInt(departureNumber));

        Customer userCustomer = (Customer) UserController.getInstance().getSessionUser();

        Request request = new Request(userCustomer, vehicle, workerSelected, destination, departure);

        VehicleController.getInstance().addRequestInVehicle(request, vehicle, workerSelected);

        System.out.println(request);
        System.out.println(vehicle.getRequests());
    }

    public static RequestController getInstance() {
        return instance;
    }
}
