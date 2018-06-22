package view;

import control.UserController;
import dao.UserDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Request;
import model.Vehicle;
import model.Worker;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RequestsWorkerFXMLController implements Initializable {

    @FXML
    private TableView<Request> requestTable;

    @FXML
    private TableColumn<Request, Integer> requestsTableStatus;

    @FXML
    private TableColumn<Request, String> requestsTableCustomer;

    @FXML
    private TableColumn<Request, String> requestsTableDeparture;

    @FXML
    private TableColumn<Request, String> requestsTableDestination;

    @FXML
    private TableColumn<Request, String> requestsTableVehicle;

    @FXML
    private TableColumn<Request, Double> requestsTableValue;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public ObservableList<Request> requestObservableList() {
        Worker worker = (Worker) UserController.getSessionUser();
        List<Vehicle> vehicleList = worker.getVehicles();
        List<Request> requestList = vehicleList
                .stream()
                .map(Vehicle::getRequests)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return null;
    }
}
