package view;

import control.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Worker;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchFXMLController implements Initializable {

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
    private MenuItem navRequestsClient;

    @FXML
    private TextField city;

    @FXML
    public void changeAccount() {
        ChangeUser changeUser = new ChangeUser();
        goQuitAction();
        try {
            changeUser.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteAccount() {
        DeleteAccount deleteAccount = new DeleteAccount();
        goQuitAction();
        try {
            deleteAccount.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Worker> loadTable() {
        double rating;

        if (ratingComboBox.getSelectionModel().getSelectedItem() == null) {
            rating = 0;
        } else {
            rating = ratingComboBox.getSelectionModel().getSelectedItem();
        }
        ObservableList<Worker> workerObservableList = FXCollections.observableArrayList(
                UserController.getInstance().searchWorkerList(city.getText(), rating)
        );

        return workerObservableList;
    }

//    TODO trazer todos os workers quando abrir a tela
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

        if (worker != null) {
            try {
                detailsRequest.start(new Stage());
                Search.getStage().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Nenhuma trabalhador selecionado");
            alert.setContentText("Você deve selecionar um trabalhador");
            alert.show();
        }

    }

    @FXML
    public void goQuitAction() {
        Search.getStage().close();
    }

    @FXML
    public void goRequestsClientAction() {
        RequestsClient requestsClient = new RequestsClient();
        goQuitAction();
        try {
            requestsClient.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Integer> observableList = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        ratingComboBox.setItems(observableList);
        loadTableAction();
    }
}
