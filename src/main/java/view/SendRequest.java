package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SendRequest extends Application{

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sendRequest.fxml"));
        Parent sendRequest;
        sendRequest = loader.load();
        Scene scene = new Scene(sendRequest);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        SendRequest.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
