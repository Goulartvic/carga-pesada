package view;

import control.UserController;
import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

        if (UserController.getInstance().userExist(txtLogin.getText())) {

            UserController.getInstance().createAccount(txtName.getText(), txtCPF.getText(),txtLogin.getText(),
                    txtPassword.getText(), txtPhone.getText(), txtUserType.getText(), txtCity.getText(), txtNumber.getText(),
                    txtState.getText(), txtStreet.getText());


            Login login = new Login();
            goQuitAction();
            try {
                login.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Já existe um usuário cadastrado com esse nome");
            alert.setContentText("Verifique os dados digitados");
            alert.show();
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
