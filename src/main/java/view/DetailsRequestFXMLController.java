package view;

import control.VehicleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private Label txtName;

    @FXML
    private Label txtTelefone;

    @FXML
    private Label txtNota;

    private Worker userWorker;

    public ObservableList<Vehicle> loadTable() {
        List<Vehicle> vehicles = VehicleController.getInstance().listWorkerVehicles(userWorker.getUserId());

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
        this.userWorker = DetailsRequest.getInstance().getSelectedWorker();
        SendRequest.getInstance().setUserSelected(userWorker);
        txtName.setText(userWorker.getName());
        txtTelefone.setText(userWorker.getPassword());
        txtNota.setText(String.valueOf(userWorker.getRating()));
        loadTableAction();
    }

    @FXML
    public void confirmAction() {
        Vehicle vehicle = tableView.getSelectionModel().getSelectedItem();
        SendRequest sendRequest = SendRequest.getInstance();
        sendRequest.setVehicleSelected(vehicle);
        sendRequest.setUserSelected(userWorker);
        try {
            sendRequest.start(new Stage());
            Search.getStage().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goQuitAction() {
        DetailsRequest.getStage().close();
    }

    @FXML
    public void cancelAction() {
        Search search = new Search();
        goQuitAction();
        try {
            search.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
