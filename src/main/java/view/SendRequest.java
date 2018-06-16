package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import model.Vehicle;

public class SendRequest extends Application {

    private static Stage stage;
    private User userSelected;
    private Vehicle vehicleSeleVehicle;

//    private static SendRequest instance = new SendRequest();

    @Override
    public void start(Stage stage) throws Exception {
        Parent sendRequest = FXMLLoader.load(getClass().getResource("SendRequest.fxml"));
        Scene scene = new Scene(sendRequest);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

//    public static SendRequest getInstance() {
//        return instance;
//    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SendRequest.stage = stage;
    }

    public User getUserSelected() {
        return userSelected;
    }

    public void setUserSelected(User userSelected) {
        this.userSelected = userSelected;
    }

    public Vehicle getVehicleSeleVehicle() {
        return vehicleSeleVehicle;
    }

    public void setVehicleSeleVehicle(Vehicle vehicleSeleVehicle) {
        this.vehicleSeleVehicle = vehicleSeleVehicle;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
