package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Vehicle;
import model.Worker;

public class SendRequest extends Application {

    private static Stage stage;
    private Worker workerSelected;
    private Vehicle vehicleSelected;

    private static SendRequest instance = new SendRequest();

    @Override
    public void start(Stage stage) throws Exception {
        Parent sendRequest = FXMLLoader.load(getClass().getResource("SendRequest.fxml"));
        Scene scene = new Scene(sendRequest);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static SendRequest getInstance() {
        if (instance == null) {
            instance = new SendRequest();
        }
        return instance;
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SendRequest.stage = stage;
    }

    public Worker getUserSelected() {
        return workerSelected;
    }

    public void setUserSelected(Worker userSelected) {
        this.workerSelected = userSelected;
    }

    public Vehicle getVehicleSelected() {
        return vehicleSelected;
    }

    public void setVehicleSelected(Vehicle vehicleSelected) {
        this.vehicleSelected = vehicleSelected;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
