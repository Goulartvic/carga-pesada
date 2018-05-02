package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Login extends Application {
    private static Stage stage;

    public void start(Stage stage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.show();
        setStage(stage);

    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
