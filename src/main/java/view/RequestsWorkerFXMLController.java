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
    private TableColumn<Request, String> requestsTableStatus;

    @FXML
    private TableColumn<Request, String> requestsTableCustomer;

    @FXML
    private TableColumn<Request, String> requestsTableDeparture;

    @FXML
    private TableColumn<Request, String> requestsTableDestination;

    @FXML
    private TableColumn<Request, String> requestsTableVehicle;

    @FXML
    private TableColumn<Request, String> requestsTableValue;

    @FXML


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTableAction();
    }

    public ObservableList<Request> loadTable() {
        Worker worker = (Worker) UserController.getSessionUser();
        List<Vehicle> vehicleList = worker.getVehicles();
        List<Request> requestList = vehicleList
                .stream()
                .map(Vehicle::getRequests)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return FXCollections.observableArrayList(requestList);
    }

    public void loadTableAction() {
        requestsTableCustomer.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getCustomer().getName())
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

    public void acceptRequest() {
        Request request = requestTable.getSelectionModel().getSelectedItem();

        if (request.getVehicle().isAvailable() && request.getStatus().getStatus()==2) {
            request.setStatus(1);
            RequestController.getInstance().update(request);
            request.getVehicle().setAvailable(false);
            VehicleController.getInstance().update(request.getVehicle());

            RequestsWorker requestsWorker = new RequestsWorker();
            try {
                RequestsWorker.getStage().close();
                requestsWorker.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Veiculo não esta disponivel");
            alert.setContentText("VEICULO NÃO ESTÁ DISPONIVEL");
            alert.show();
        }


    }

    public void declineRequest() {
        Request request = requestTable.getSelectionModel().getSelectedItem();

        if (request.getStatus().getStatus()==2) {
            request.setStatus(3);
            RequestController.getInstance().update(request);

            RequestsWorker requestsWorker = new RequestsWorker();
            try {
                RequestsWorker.getStage().close();
                requestsWorker.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("ERRO");
            alert.setContentText("SÓ É POSSIVEL RECUSAR SOLICITAÇÕES ON HOLD");
            alert.show();
        }
    }
}
