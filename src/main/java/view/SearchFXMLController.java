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
import model.SearchMock;
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
    private ComboBox<Integer> rating;

    @FXML
    private TextField city;

    public ObservableList<Worker> loadTable() {
        ObservableList<Worker> workerObservableList = FXCollections.observableArrayList(
                UserController.getInstance().searchWorkerList(city.getText(), rating.getSelectionModel().getSelectedItem())
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(1,2,3,4,5);
        rating.setItems(observableList);
    }
}
