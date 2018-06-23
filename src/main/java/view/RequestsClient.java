package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RequestsClient extends Application{

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("requestsClient.fxml"));
        Parent requestsClient;
        requestsClient = loader.load();
        Scene scene = new Scene(requestsClient);
        stage.setTitle("Solicitações");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RequestsClient.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
