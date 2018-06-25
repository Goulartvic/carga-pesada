package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Vehicle;

public class ChangeVehicle extends Application {

    private static Stage stage;
    private static ChangeVehicle instance = new ChangeVehicle();
    private Vehicle vehicleSelected;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeVehicle.fxml"));
        Parent changeUser = loader.load();
        Scene scene = new Scene(changeUser);
        stage.setTitle("Alterar Veículo");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public Vehicle getVehicleSelected() {
        return vehicleSelected;
    }

    public void setVehicleSelected(Vehicle vehicleSelected) {
        this.vehicleSelected = vehicleSelected;
    }

    public static ChangeVehicle getInstance() {
        return instance;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ChangeVehicle.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
