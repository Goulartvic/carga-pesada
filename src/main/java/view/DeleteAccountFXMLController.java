package view;

import control.UserController;
import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

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
}
