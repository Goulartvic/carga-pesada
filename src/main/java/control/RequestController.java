package control;

import dao.RequestDao;
import model.*;

import java.sql.SQLException;

public class RequestController {

    private static RequestController instance = new RequestController();

    public RequestController() {
        super();
    }


    public void createRequest(String departureStreet, String departureNumber, String departureCity, String departureState,
                              String destinationStreet, String destinationNumber, String destinationCity, String destinationState, Vehicle vehicle, Worker workerSelected) throws Exception {

        Address destination = new Address(destinationCity, destinationState, destinationStreet, Integer.parseInt(destinationNumber));
        Address departure = new Address(departureCity, departureState, departureStreet, Integer.parseInt(departureNumber));


        if (!AddressController.getAddressInstance().verifySameAddresses(destination, departure)) {
            Customer userCustomer = (Customer) UserController.getInstance().getSessionUser();

            AddressController.getAddressInstance().saveAddress(departure);
            AddressController.getAddressInstance().saveAddress(destination);

            Request request = new Request(userCustomer, vehicle, workerSelected, destination, departure);

            userCustomer.getRequests().add(request);
            RequestDao.getInstance().addRequest(request);
        } else {
            throw new Exception();
        }

    }

    public void update(Request request) {
        try {
            RequestDao.getInstance().update(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static RequestController getInstance() {
        return instance;
    }
}
