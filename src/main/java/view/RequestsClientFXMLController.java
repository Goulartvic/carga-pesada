package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RequestsClientFXMLController {

    @FXML
    private Button btnCancel;

    @FXML
    private TableColumn<?, ?> requestsTableStatus;

    @FXML
    private TableColumn<?, ?> requestsTableWorker;

    @FXML
    private TableColumn<?, ?> requestsTableDeparture;

    @FXML
    private TableColumn<?, ?> requestsTableDestination;

    @FXML
    private TableColumn<?, ?> requestsTableVehicle;

    @FXML
    private TableColumn<?, ?> requestsTableValue;

    @FXML
    private TableView<?> requestsTable;

    @FXML
    private Button btnConfirm;

    @FXML
    void btnCancelAction() {

    }

    @FXML
    void btnConfirmAction() {

    }

}
