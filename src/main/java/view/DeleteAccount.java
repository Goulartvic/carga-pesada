package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DeleteAccount extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        Parent deleteAccount;
        deleteAccount = FXMLLoader.load(getClass().getResource("deleteAccount.fxml"));
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
