package view;

import control.VehicleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterVehicleFXMLController implements Initializable {

    @FXML
    private ComboBox<Integer> size;

    @FXML
    private CheckBox intercity;

    @FXML
    private TextField brand;

    @FXML
    private TextField model;

    @FXML
    private TextField plate;

    @FXML
    private TextField kmPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> comboBox = FXCollections.observableArrayList(1, 2, 3);
        size.setItems(comboBox);
    }

    public void saveVehicle() {
        if (!brand.getText().isEmpty() && !model.getText().isEmpty() && !plate.getText().isEmpty() && !kmPrice.getText().isEmpty()) {

            VehicleController.getInstance().save(brand.getText(), model.getText(), plate.getText(), intercity.isSelected(),
                    size.getSelectionModel().getSelectedItem(), kmPrice.getText());

            RequestsWorker requestsWorker = new RequestsWorker();
            goQuitAction();
            try {
                requestsWorker.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Verifique os campos");
            alert.setContentText("Você deve preencher todos os campos");
            alert.show();
        }
    }

    @FXML
    public void goQuitAction() {
        RegisterVehicle.getStage().close();
    }

    @FXML
    public void changeAccountAction() {
        ChangeUser changeUser = new ChangeUser();
        goQuitAction();
        try {
            changeUser.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAccountAction() {
        DeleteAccount deleteAccount = new DeleteAccount();
        goQuitAction();
        try {
            deleteAccount.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void listVehiclesAction() {
        ListVehicles listVehicles = new ListVehicles();
        goQuitAction();
        try {
            listVehicles.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
