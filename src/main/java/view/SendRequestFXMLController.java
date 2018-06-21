package view;

import control.RequestController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Vehicle;
import model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class SendRequestFXMLController implements Initializable {


    @FXML
    private TextField departureState;

    @FXML
    private TextField departureStreet;

    @FXML
    private TextField departureCity;

    @FXML
    private TextField departureNumber;

    @FXML
    private Label labelVehicle;

    @FXML
    private Button btnCancel;

    @FXML
    private Label labelWorker;

    @FXML
    private Button btnSendRequest;

    @FXML
    private Label labelPlate;

    @FXML
    private TextField arrivalDestinationNumber;

    @FXML
    private TextField arrivalDestinationStreet;

    @FXML
    private TextField arrivalDestinationCity;

    @FXML
    private TextField arrivalDestinationState;

    private Worker workerSelected;

    private Vehicle vehicleSelected;

    @FXML
    public void goQuitAction() {

    }

    @FXML
    public void sendRequestAction() {
        try {
            RequestController.getInstance().createRequest(departureStreet.getText(), departureNumber.getText(), departureCity.getText(), departureState.getText(),
                    arrivalDestinationStreet.getText(), arrivalDestinationNumber.getText(), arrivalDestinationCity.getText(), arrivalDestinationState.getText(),
                    vehicleSelected, workerSelected);

            Search search = new Search();
            search.start(new Stage());
            SendRequest.getStage().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.workerSelected = SendRequest.getInstance().getUserSelected();
        this.vehicleSelected = SendRequest.getInstance().getVehicleSelected();
        labelWorker.setText(workerSelected.getName());
        labelVehicle.setText(vehicleSelected.getModel());
        labelPlate.setText(vehicleSelected.getPlate());
    }
}
