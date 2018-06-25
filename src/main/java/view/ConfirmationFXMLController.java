package view;

import control.RequestController;
import control.UserController;
import control.VehicleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Request;
import model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationFXMLController implements Initializable {

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField rating;

    private Request request;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> comboBoxValues = FXCollections.observableArrayList("Sim", "Não");
        comboBox.setItems(comboBoxValues);
        request = Confirmation.getInstance().getSelectedRequest();
    }

    public void onAction() {
        if (!rating.getText().isEmpty() && !comboBox.getSelectionModel().isEmpty()) {
            request.setStatus(4);
            RequestController.getInstance().update(request);
            request.getVehicle().setAvailable(true);
            VehicleController.getInstance().update(request.getVehicle());
            double workerRating = request.getWorker().getRating();
            double newRating = Double.parseDouble(rating.getText());
            request.getWorker().setRating((workerRating*3 + newRating)/4);
            UserController.getInstance().updateRating(request.getWorker());

            RequestsClient requestsClient = new RequestsClient();
            try {
                requestsClient.start(new Stage());
                Confirmation.getStage().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos não preenchidos");
            alert.setContentText("Você deve preencher todos os campos");
            alert.show();
        }
    }

    public void cancelAction() {
        RequestsClient requestsClient = new RequestsClient();
        try {
            requestsClient.start(new Stage());
            Confirmation.getStage().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
