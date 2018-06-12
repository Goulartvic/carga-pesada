package view;

import control.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFXMLController {

    private static Stage stage;
    @FXML
    private TextField txtLogin;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    public void goQuitAction() {
        Login.getStage().close();
    }

    @FXML
    public void loginAction() {
        if (UserController.getInstance().userIsValid(txtLogin.getText(), txtPassword.getText())) {
            UserController.getInstance().loginUser(txtLogin.getText(), txtPassword. getText());

//            User sessionUser = UserController.getSessionUser();
//            if (UserController.getSessionUser().getUserType() == UserType.WORKER) {
//                UserController.getSessionUser() = new Worker();
//            }

            DeleteAccount deleteAccount = new DeleteAccount();
            goQuitAction();
            try {
                deleteAccount.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Login Inválido");
            alert.setContentText("Verifique os dados digitados");
            alert.show();
        }
    }

    @FXML
    public void createAction() {
        RegisterUser registerUser = new RegisterUser();
        goQuitAction();
        try {
            registerUser.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
