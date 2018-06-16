package view;

import control.RequestController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Vehicle;
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

    private Worker workerSelected;

    private Vehicle vehicleSelected;


    public SendRequestFXMLController(Worker workerSelected, Vehicle vehicleSelected) {
//        this.workerSelected = (Worker) SendRequest.getInstance().getUserSelected();
//        this.vehicleSelected = SendRequest.getInstance().getVehicleSeleVehicle();
//        this.labelWorker.setText(workerSelected.getName());
//        this.labelVehicle.setText(vehicleSelected.getModel());
//        this.labelPlate.setText(vehicleSelected.getPlate());
    }

    @FXML
    public void goQuitAction() {

    }

    @FXML
    public void sendRequestAction() {
        RequestController.getInstance().createRequest(txtArrivalDestination.getText(), txtDeparture.getText(), vehicleSelected, workerSelected);
    }


}
