package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ListVehicles extends Application{

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listVehicles.fxml"));
        Parent listVehicles;
        listVehicles = loader.load();
        Scene scene = new Scene(listVehicles);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListVehicles.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
