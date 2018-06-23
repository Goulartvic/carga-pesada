package view;

import control.UserController;
import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.Worker;

public class DeleteAccountFXMLController {

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtNome;

    @FXML
    public void deleteAction() {
        UserController.getSessionUser().setUsername(txtNome.getText());
        UserController.getInstance().deleteUser();
    }

    @FXML
    public void goQuitAction() {
        DeleteAccount.getStage().close();
    }

    @FXML
    public void cancelAction() {
        if (UserController.getSessionUser() instanceof Worker) {
            RequestsWorker requestsWorker = new RequestsWorker();
            goQuitAction();
            try {
                requestsWorker.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Search search = new Search();
            goQuitAction();
            try {
                search.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
