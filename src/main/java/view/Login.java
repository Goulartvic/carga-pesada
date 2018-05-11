package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Login extends Application {
    private static Stage stage;

    private static LoginFXMLController loginController;

    private User sessionUser;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent login;
        login = loader.load();
        loginController = loader.getController();
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
