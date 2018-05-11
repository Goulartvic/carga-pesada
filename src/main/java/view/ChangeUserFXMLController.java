package view;

import dao.AddressDao;
import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import model.User;
import javafx.scene.control.TextField;

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

    private User sessionUser;

    @FXML
    public void changeUserAction() {
        UserDao userDao = new UserDao();
        AddressDao addressDao = new AddressDao();
        User paramUser = new User(sessionUser, sessionUser.getAddress());

        if (nameTxt.getText().equals("")==false){sessionUser.setName(nameTxt.getText());}

        if (passwordTxt.getText().equals("")==false) {sessionUser.setPassword(passwordTxt.getText());}

        if (stateTxt.getText().equals("")==false) {sessionUser.getAddress().setState(stateTxt.getText());}

        if (cityTxt.getText().equals("")==false) {sessionUser.getAddress().setCity(cityTxt.getText());}

        if (streetTxt.getText().equals("")==false) {sessionUser.getAddress().setStreet(streetTxt.getText());}

        if (numberTxt.getText().equals("")==false) {sessionUser.getAddress().setNumber(Integer.parseInt(numberTxt.getText()));}

        if (phoneTxt.getText().equals("")==false) {sessionUser.setPhoneNumber(phoneTxt.getText());}

        if (sessionUser==paramUser) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hey Look!");
            alert.setHeaderText("Erro ao atualizar");
            alert.setContentText("Você não está atualizando nenhum campo\"");

            alert.showAndWait();
        } else {
            userDao.update(sessionUser);
            addressDao.update(sessionUser);
        }
    }

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }
}
