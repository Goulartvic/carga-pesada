package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class DeleteAccount extends Application {
    private static Stage stage;

    private static DeleteAccountFXMLController deleteController;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteAccount.fxml"));
        Parent deleteAccount = loader.load();
        Scene scene = new Scene(deleteAccount);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DeleteAccount.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
