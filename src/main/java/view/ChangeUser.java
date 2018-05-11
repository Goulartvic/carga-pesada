package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class ChangeUser extends Application {

    private static Stage stage;

    private static ChangeUserFXMLController changeUserController;

    private User sessionUser;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("changeUser.fxml"));
        Parent changeUser = loader.load();
        Scene scene = new Scene(changeUser);
        changeUserController = loader.getController();
        changeUserController.setSessionUser(sessionUser);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ChangeUser.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
