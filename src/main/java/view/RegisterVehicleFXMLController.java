package view;

import control.VehicleController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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
        ObservableList<Integer> comboBox = FXCollections.observableArrayList(1,2,3);
        size.setItems(comboBox);
    }

    public void saveVehicle() {
        VehicleController.getInstance().save(brand.getText(), model.getText(), plate.getText(), intercity.isSelected(), size.getSelectionModel().getSelectedItem(), kmPrice.getText());
    }
}
