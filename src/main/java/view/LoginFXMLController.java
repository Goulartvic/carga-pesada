package view;

import control.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Worker;

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
        if (!txtLogin.getText().isEmpty() && !txtPassword.getText().isEmpty()) {

            if (UserController.getInstance().userIsValid(txtLogin.getText(), txtPassword.getText())) {
                UserController.getInstance().loginUser(txtLogin.getText(), txtPassword.getText());

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
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Login Inválido");
                alert.setContentText("Verifique os dados digitados");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Campos em branco");
            alert.setContentText("Você deve preencher o campos para prosseguir");
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
