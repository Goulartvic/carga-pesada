package view;

import control.UserController;
import dao.UserDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ListUserFXMLController implements Initializable{

    @FXML
    private TableView<User> tableView;

    @FXML
    private TableColumn<User, String> nameCollumn;

    @FXML
    private TableColumn<User, String> cpfCollumn;

    @FXML
    private TableColumn<User, String> phoneCollumn;

    @FXML
    private TableColumn<User, Integer> userTypeCollumn;

    @FXML
    private TableColumn<User, Integer> ratingCollumn;

    public ObservableList<User> loadTable() {
        ObservableList<User> users = FXCollections.observableArrayList(UserController.getInstance().searchAll());
        return users;
    }

    public void loadTableAction() {
        nameCollumn.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        cpfCollumn.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
        phoneCollumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));
        userTypeCollumn.setCellValueFactory(
                new PropertyValueFactory<>("userType"));
        ratingCollumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));
        tableView.setItems(loadTable());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }
}
