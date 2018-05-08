package view;

import dao.AddressDao;
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

    private User sessionUser;

    @FXML
    public void deleteAction() {
        UserDao userDao = new UserDao();
        AddressDao addressDao = new AddressDao();
        User user = new User();
        user.setUsername(txtNome.getText());
        addressDao.delete(user);
        userDao.delete(user);
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
