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
import model.Vehicle;

import java.net.URL;
import java.util.ResourceBundle;

public class ChangeVehicleFXMLController implements Initializable {

    @FXML
    private TextField kmPrice;

    @FXML
    private CheckBox intercity;

    @FXML
    private ComboBox<Integer> size;

    private Vehicle vehicle;

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
    public void goQuitAction() {
        ChangeVehicle.getStage().close();
    }

    @FXML
    public void changeVehicle() {
        this.vehicle.setKmPrice(Double.parseDouble(kmPrice.getText()));
        this.vehicle.setVehicleSize(size.getSelectionModel().getSelectedItem());
        this.vehicle.setIntercity(intercity.isSelected());
        if (intercity.isSelected() && !kmPrice.getText().isEmpty()) {
            VehicleController.getInstance().update(vehicle);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Verifique os campos");
            alert.setContentText("Você deve preencher todos os campos");
            alert.show();
        }
    }

    @FXML
    public void goListVehicle() {
        ListVehicles listVehicles = new ListVehicles();
        goQuitAction();
        try {
            listVehicles.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.vehicle = ChangeVehicle.getInstance().getVehicleSelected();
        System.out.println(vehicle);
//        kmPrice.setText(String.valueOf(vehicle.getKmPrice()));
//        intercity.setSelected(vehicle.isIntercity());
        ObservableList<Integer> comboBox = FXCollections.observableArrayList(1, 2, 3);
        size.setItems(comboBox);
    }
}
