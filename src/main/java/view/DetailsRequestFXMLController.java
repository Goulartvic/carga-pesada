package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DetailsRequestFXMLController implements Initializable {

    @FXML
    private TableView<Vehicle> tableView;

    @FXML
    private TableColumn<Vehicle, String> brandCollumn;

    @FXML
    private TableColumn<Vehicle, String> plateCollumn;

    @FXML
    private TableColumn<Vehicle, String> modelCollumn;

    @FXML
    private TableColumn<Vehicle, Boolean> intercityCollumn;

    @FXML
    private TableColumn<Vehicle, String> kmPriceCollumn;

    private Address address;
    private Worker userWorker;

    public ObservableList<Vehicle> loadTable() {

        address = new Address("Floripa", "SC", "Rua da rua", 110);
        userWorker = new Worker(address, "100.100.100-10", "Jose", "123", "9999-9999", 1, "josegameplays");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        Vehicle vehicle1 = new Vehicle("Mercedes", "Atego 1728", "AAA8888", VehicleSize.MEDIUM, true, 1000);
        Vehicle vehicle2 = new Vehicle("Volvo", "Globetrotter", "ABC8974", VehicleSize.BIG, true, 1500);
        Vehicle vehicle3 = new Vehicle("Scania", "Streamline ", "CCC5656", VehicleSize.MEDIUM, false, 800);
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        userWorker.setVehicles(vehicles);

        ObservableList<Vehicle> vehiclesObs = FXCollections.observableArrayList(vehicles);
        return vehiclesObs;
    }

    public void loadTableAction() {
        brandCollumn.setCellValueFactory(
                new PropertyValueFactory<>("brand"));
        modelCollumn.setCellValueFactory(
                new PropertyValueFactory<>("model"));
        plateCollumn.setCellValueFactory(
                new PropertyValueFactory<>("plate"));
        intercityCollumn.setCellValueFactory(
                new PropertyValueFactory<>("intercity"));
        kmPriceCollumn.setCellValueFactory(
                new PropertyValueFactory<>("kmPrice"));
        tableView.setItems(loadTable());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }

    @FXML
    public void confirmAction() {
        Vehicle vehicle = tableView.getSelectionModel().getSelectedItem();
        SendRequest sendRequest = SendRequest.getInstance();
        sendRequest.setVehicleSeleVehicle(vehicle);
        sendRequest.setUserSelected(userWorker);

        try {
            sendRequest.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelAction() {

    }
}
