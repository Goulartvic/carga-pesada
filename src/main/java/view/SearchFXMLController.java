package view;

import control.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchFXMLController implements Initializable{

    @FXML
    private TableView<Worker> tableView;

    @FXML
    private TableColumn<Worker, String> customerCollumn;

    @FXML
    private TableColumn<Worker, String> ratingCollumn;

    @FXML
    private TableColumn<Worker, String> phoneNumber;

    @FXML
    private ComboBox<Integer> ratingComboBox;

    @FXML
    private TextField city;

    public ObservableList<Worker> loadTable() {
        double rating;

        if (ratingComboBox.getSelectionModel().getSelectedItem()==null) {
            rating = 0;
        }

        else {
            rating= ratingComboBox.getSelectionModel().getSelectedItem();
        }
        ObservableList<Worker> workerObservableList = FXCollections.observableArrayList(
                UserController.getInstance().searchWorkerList(city.getText(), rating)
        );

        return workerObservableList;
    }

    public void loadTableAction() {
        customerCollumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        ratingCollumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));
        phoneNumber.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));

        tableView.setItems(loadTable());
    }

    @FXML
    public void workerDetail() {
        Worker worker = tableView.getSelectionModel().getSelectedItem();
        DetailsRequest detailsRequest = DetailsRequest.getInstance();
        detailsRequest.setSelectedWorker(worker);

        try {
            detailsRequest.start(new Stage());
            Search.getStage().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10);
        ratingComboBox.setItems(observableList);
    }
}
