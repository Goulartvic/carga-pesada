package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SearchMock;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchFXMLController implements Initializable{

    @FXML
    private TableView<SearchMock> tableView;

    @FXML
    private TableColumn<SearchMock, String> customerCollumn;

    @FXML
    private TableColumn<SearchMock, String> ratingCollumn;

    @FXML
    private TableColumn<SearchMock, String> kmPriceCollumn;

    public ObservableList<SearchMock> loadTable() {
        ObservableList<SearchMock> searchMocks = FXCollections.observableArrayList(
                new SearchMock("Jorge", "4", "500")
        );

        return searchMocks;
    }

    public void loadTableAction() {
        customerCollumn.setCellValueFactory(
                new PropertyValueFactory<>("customer"));
        ratingCollumn.setCellValueFactory(
                new PropertyValueFactory<>("rating"));
        kmPriceCollumn.setCellValueFactory(
                new PropertyValueFactory<>("kmPrice"));

        tableView.setItems(loadTable());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTableAction();
    }
}
