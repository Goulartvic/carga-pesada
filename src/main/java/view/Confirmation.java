package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Confirmation extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Confirmation.fxml"));
        Parent confirmation;
        confirmation = loader.load();
        Scene scene = new Scene(confirmation);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Confirmation.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
