package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RequestsWorker extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("requestsWorker.fxml"));
        Parent requestsWorker;
        requestsWorker = loader.load();
        Scene scene = new Scene(requestsWorker);
        stage.setTitle("Solicitações");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RequestsWorker.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
