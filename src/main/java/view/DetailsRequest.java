package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Worker;

public class DetailsRequest extends Application {
    private static Stage stage;

    private static DetailsRequest instance = new DetailsRequest();

    private Worker selectedWorker;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsRequest.fxml"));
        Parent detailsRequest;
        detailsRequest = loader.load();
        Scene scene = new Scene(detailsRequest);
        stage.setTitle("Detalhes da solicitação");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        DetailsRequest.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static DetailsRequest getInstance() {
        return instance;
    }

    public Worker getSelectedWorker() {
        return selectedWorker;
    }

    public void setSelectedWorker(Worker selectedWorker) {
        this.selectedWorker = selectedWorker;
    }
}
