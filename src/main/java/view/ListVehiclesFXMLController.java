package view;

import control.UserController;
import control.VehicleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Vehicle;
import model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class ListVehiclesFXMLController implements Initializable {

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
                ((Worker) UserController.getSessionUser()).getVehicles()
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

    @FXML
    public void goRequests() {
        RequestsWorker requestsWorker = new RequestsWorker();
        goQuitAction();
        try {
            requestsWorker.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goQuitAction() {
        ListVehicles.getStage().close();
    }

    @FXML
    public void deleteVehicle() {
        Vehicle vehicle = tableView.getSelectionModel().getSelectedItem();
        if (vehicle != null) {
            VehicleController.getInstance().deleteVehicle(vehicle);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum veículo selecionado");
            alert.setContentText("Você deve selecionar um veículo");
            alert.show();
        }
    }

    @FXML
    public void changeVehicle() {
//        TODO - Implementar alterar veiculo
        ChangeVehicle changeVehicle = new ChangeVehicle();
        goQuitAction();
        Vehicle vehicle = tableView.getSelectionModel().getSelectedItem();
        if (vehicle != null) {
            changeVehicle.setVehicleSelected(vehicle);
            System.out.println("veiculo selecionado na lista"+vehicle);
            try {
                changeVehicle.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhum veículo selecionado");
            alert.setContentText("Você deve selecionar um veículo");
            alert.show();
        }
    }

    @FXML
    public void registerVehicle() {
        RegisterVehicle registerVehicle = new RegisterVehicle();
        goQuitAction();
        try {
            registerVehicle.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }
}
