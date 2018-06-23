package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Vehicle;
import model.VehicleSize;

import java.net.URL;
import java.util.ResourceBundle;

public class ListVehiclesFXMLController implements Initializable {

//    TODO - listar veiculos do worker(sessionUser)

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

    @FXML
    private TableColumn<Vehicle, Boolean> availableCollumn;

    @FXML
    private TableColumn<Vehicle, String> vehicleSizeCollumn;

    public ObservableList<Vehicle> loadTable() {
        ObservableList<Vehicle> vehicles = FXCollections.observableArrayList(
                new Vehicle("Mercedes", "Atego 1728", "AAA8888", VehicleSize.MEDIUM, true, 1000),
                new Vehicle("Volvo", "Globetrotter", "ABC8974", VehicleSize.BIG, true, 1500),
                new Vehicle("Scania", "Streamline ", "CCC5656", VehicleSize.MEDIUM, false, 800)
        );
        return vehicles;
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
        availableCollumn.setCellValueFactory(
                new PropertyValueFactory<>("available"));
        vehicleSizeCollumn.setCellValueFactory(
                new PropertyValueFactory<>("vehicleSize")
        );
        tableView.setItems(loadTable());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }
}
