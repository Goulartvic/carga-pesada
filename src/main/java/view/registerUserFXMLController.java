package view;

import control.UserController;
import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import model.UserType;
import model.Worker;

import java.util.ArrayList;

public class registerUserFXMLController {

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtName;

    @FXML
    private ComboBox<Integer> cbUserType;

    @FXML
    private TextField txtNumber;

    @FXML
    private Button btnSaveUser;

    @FXML
    private TextField txtCPF;

    @FXML
    private TextField txtState;

    @FXML
    private TextField txtStreet;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserType;

    @FXML
    private TextField txtPhone;

    public registerUserFXMLController() {
    }

    @FXML
    public void saveUserAction() {
        UserController.getInstance().createAccount(txtName.getText(), txtCPF.getText(),txtLogin.getText(),
                txtPassword.getText(), txtPhone.getText(), txtUserType.getText(), txtCity.getText(), txtNumber.getText(),
                txtState.getText(), txtStreet.getText());

        if (UserController.getInstance().userIsValid(txtLogin.getText(), txtPassword.getText())) {
            UserController.getInstance().loginUser(txtLogin.getText(), txtPassword.getText());

            User sessionUser = UserController.getSessionUser();

            if (sessionUser.getUserType() == UserType.WORKER) {
                UserController.setSessionUser(new Worker(sessionUser.getAddress(), sessionUser.getCpf(), sessionUser.getName(),
                        sessionUser.getPassword(), sessionUser.getPhoneNumber(), sessionUser.getUserId(), sessionUser.getUsername()));


            }
        }
        Login login = new Login();
        goQuitAction();
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loginAction() {
        Login login = new Login();
        goQuitAction();
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goQuitAction() {
        RegisterUser.getStage().close();
    }
}
