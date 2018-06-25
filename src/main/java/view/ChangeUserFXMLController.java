package view;

import control.UserController;
import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Customer;
import model.User;
import javafx.scene.control.TextField;
import model.Worker;

public class ChangeUserFXMLController {

    @FXML
    private TextField nameTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private TextField stateTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField streetTxt;

    @FXML
    private TextField numberTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    public void goQuitAction() {
        ChangeUser.getStage().close();
    }

    @FXML
    public void changeUserAction() {
        User paramUser;

        if (UserController.getSessionUser() instanceof Customer) {
            paramUser = new Customer((Customer) UserController.getSessionUser());
        } else {
            paramUser = new Worker((Worker) UserController.getSessionUser());
        }
        if (nameTxt.getText().equals("") == false) {
            UserController.getSessionUser().setName(nameTxt.getText());
        }

        if (passwordTxt.getText().equals("") == false) {
            UserController.getSessionUser().setPassword(passwordTxt.getText());
        }

        if (stateTxt.getText().equals("") == false) {
            UserController.getSessionUser().getAddress().setState(stateTxt.getText());
        }

        if (cityTxt.getText().equals("") == false) {
            UserController.getSessionUser().getAddress().setCity(cityTxt.getText());
        }

        if (streetTxt.getText().equals("") == false) {
            UserController.getSessionUser().getAddress().setStreet(streetTxt.getText());
        }

        if (numberTxt.getText().equals("") == false) {
            UserController.getSessionUser().getAddress().setNumber(Integer.parseInt(numberTxt.getText()));
        }

        if (phoneTxt.getText().equals("") == false) {
            UserController.getSessionUser().setPhoneNumber(phoneTxt.getText());
        }

        if (UserController.getSessionUser() == paramUser) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hey Look!");
            alert.setHeaderText("Erro ao atualizar");
            alert.setContentText("Você não está atualizando nenhum campo\"");

            alert.showAndWait();
        } else {
            UserController.getInstance().changeUser();
            Login login = new Login();
            try {
                login.start(new Stage());
                goQuitAction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
