package view;

import dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Address;
import model.User;
import model.UserType;

import javax.xml.soap.Text;


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


    public registerUserFXMLController() {
    }

    @FXML
    public void saveUserAction() {
        UserDao userDao = new UserDao();
        User user = new User();
        Address address = new Address();
        user.setName(txtName.getText());
        user.setCpf(txtCPF.getText());
        user.setUsername(txtLogin.getText());
        user.setPassword(txtPassword.getText());
        user.setPhoneNumber(txtNumber.getText());
        if (Integer.parseInt(txtUserType.getText()) == 1) {
            user.setUserType(UserType.CUSTOMER);
        } else {
            user.setUserType(UserType.WORKER);
        }
//        address.setCity(txtCity.getText());
//        address.setNumber(Integer.parseInt(txtNumber.getText()));
//        address.setState(txtState.getText());
//        address.setStreet(txtStreet.getText());
//        user.setAddress(address);
        userDao.save(user);
    }

}
