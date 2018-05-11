package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class ListUser extends Application {

    private static Stage stage;

    private ListUserFXMLController listUserController;

    private User sessionUser;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("listUsers.fxml"));
        Parent listUser = loader.load();
        Scene scene = new Scene(listUser);
        listUserController = loader.getController();
        listUserController.setSessionUser(sessionUser);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        ListUser.stage = stage;
    }

    public User getSessionUser() {return sessionUser;}

    public void setSessionUser(User sessionUser) {this.sessionUser = sessionUser;}
}
