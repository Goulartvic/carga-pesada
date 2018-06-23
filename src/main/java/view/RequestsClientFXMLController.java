package view;

import control.RequestController;
import control.UserController;
import control.VehicleController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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
    public void goSearchServices() {
        Search search = new Search();
        goQuitAction();
        try {
            search.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeAccount() {
        ChangeUser changeUser = new ChangeUser();
        goQuitAction();
        try {
            changeUser.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAccount() {
        DeleteAccount deleteAccount = new DeleteAccount();
        goQuitAction();
        try {
            deleteAccount.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goQuitAction() {
        RequestsClient.getStage().close();
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

    public void confirm() {
        Request request = requestTable.getSelectionModel().getSelectedItem();

        if (request.getStatus().getStatus() == 1) {
            request.setStatus(4);
            RequestController.getInstance().update(request);
            request.getVehicle().setAvailable(true);
            VehicleController.getInstance().update(request.getVehicle());

            RequestsWorker requestsWorker = new RequestsWorker();
            try {
                RequestsWorker.getStage().close();
                requestsWorker.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Veiculo não esta disponivel");
            alert.setContentText("VEICULO NÃO ESTÁ DISPONIVEL");
            alert.show();
        }


    }

    public void declineRequest() {
        Request request = requestTable.getSelectionModel().getSelectedItem();

        if (request.getStatus().getStatus() == 2) {
            request.setStatus(3);
            RequestController.getInstance().update(request);

            RequestsClient requestsClient = new RequestsClient();
            try {
                RequestsClient.getStage().close();
                requestsClient.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setHeaderText("ERRO");
            alert.setContentText("SÓ É POSSIVEL RECUSAR SOLICITAÇÕES ON HOLD!");
            alert.show();
        }
    }
}

