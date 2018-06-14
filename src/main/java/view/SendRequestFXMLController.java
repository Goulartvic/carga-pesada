package view;

import control.RequestController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Address;
import model.Vehicle;
import model.VehicleSize;
import model.Worker;

public class SendRequestFXMLController {

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtArrivalDestination;

    @FXML
    private Label labelWorker;

    @FXML
    private Button btnSendRequest;

    @FXML
    private Label labelVehicle;

    @FXML
    private TextField txtDeparture;

    @FXML
    private Label labelPlate;

    @FXML
    public void goQuitAction() {

    }

    @FXML
    public void sendRequestAction() {
        Address address = new Address("Floripa", "SC", "Rua da rua", 110);
        Worker userWorker = new Worker(address, "100.100.100-10", "Jose", "123", "9999-9999", 1, "josegameplays");
        //TODO-Remover linhas acima, pegar id do user da tabela anterior
        Vehicle vehicle = new Vehicle("Ford", "F1000", "MXL-3155", VehicleSize.SMALL, false, 5.50);
        //TODO - remover linha acima e pegar id do veiculo selecionado da tabela anterior

        RequestController.getInstance().createRequest(txtArrivalDestination.getText(), txtDeparture.getText(), labelPlate.getText(), userWorker.getUserId());
    }


}
