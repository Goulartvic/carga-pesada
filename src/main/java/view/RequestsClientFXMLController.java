package view;

import control.UserController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Request;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestsClientFXMLController implements Initializable {

    @FXML
    private TableView<Request> requestTable;

    @FXML
    private TableColumn<Request, String> requestsTableStatus;

    @FXML
    private TableColumn<Request, String> requestsTableWorker;

    @FXML
    private TableColumn<Request, String> requestsTableDeparture;

    @FXML
    private TableColumn<Request, String> requestsTableDestination;

    @FXML
    private TableColumn<Request, String> requestsTableVehicle;

    @FXML
    private TableColumn<Request, String> requestsTableValue;

    @FXML
    private Button btnConfirm;

    @FXML
    void btnCancelAction() {

    }

    @FXML
    void btnConfirmAction() {

    }

    public ObservableList<Request> loadTable() {
        Customer customer = (Customer) UserController.getSessionUser();
        return FXCollections.observableArrayList(customer.getRequests());
    }

    public void loadTableAction() {
        requestsTableWorker.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getWorker().getName())
        );
        requestsTableDeparture.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getDeparture().getCity())
        );
        requestsTableDestination.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getArrivalDestination().getCity())
        );
        requestsTableStatus.setCellValueFactory(
                new PropertyValueFactory<>("status"));
        requestsTableValue.setCellValueFactory(
                param -> new SimpleStringProperty(String.valueOf(param.getValue().getVehicle().getKmPrice())));
        requestsTableVehicle.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getVehicle().getPlate())
        );

        requestTable.setItems(loadTable());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }
}
