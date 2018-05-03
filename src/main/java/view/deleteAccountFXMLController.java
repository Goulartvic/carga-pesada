package view;

import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import model.User;

public class deleteAccountFXMLController {

    @FXML
    private Button btnDelete;

    @FXML
    private PasswordField txtNome;

    @FXML
    public void deleteAction() {
        UserDao userDao = new UserDao();
        User user = new User();

    }
}
