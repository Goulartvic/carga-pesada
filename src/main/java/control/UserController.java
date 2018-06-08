package control;

import dao.UserDao;
import model.User;
import model.UserType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private static UserController instance = new UserController();
    private static User sessionUser;
    private UserDao userDao;

    public UserController() {
        super();
    }

    public void createAccount(String name, String cpf, String username, String password, String phone, String userType,
                              String city, String number, String state, String street){
        userDao = new UserDao();
        User user = new User();
        user.setName(name);
        user.setCpf(cpf);
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        if (Integer.parseInt(userType) == 1) {
            user.setUserType(UserType.CUSTOMER.getUserType());
        } else {
            user.setUserType(UserType.WORKER.getUserType());
        }
        user.getAddress().setCity(city);
        user.getAddress().setNumber(Integer.parseInt(number));
        user.getAddress().setState(state);
        user.getAddress().setStreet(street);
        try {
            userDao.save(user);
            user.setUserId(userDao.lastUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddressController.getAddressInstance().saveAddress(user);
    }

    public void loginUser(String login, String password) {
        userDao = new UserDao();

        try {
            sessionUser = userDao.authenticateUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddressController.getAddressInstance().setAddress(sessionUser);
    }

    public boolean userIsValid(String login, String password) {
        userDao = new UserDao();
        try {
            if (userDao.returnAuthentication(login, password)) {
                return true;
            } else {return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changeUser(User user) {
        userDao = new UserDao();

        try {
            userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddressController.getAddressInstance().updateAddress(user);
    }

    public void deleteUser(){
        userDao = new UserDao();
        AddressController.getAddressInstance().deleteAddress(sessionUser);
        try {
            userDao.delete(UserController.getSessionUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> searchAll() {
        userDao = new UserDao();
        List<User> userList = new ArrayList<>();
        try {
            userList = userDao.searchAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean userExist() {
        return false;
    }

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        // Usar para setar o usuário da sessão após autenticação pelo login
        instance.sessionUser = sessionUser;
    }

    public static UserController getInstance() {
        return instance;
    }

    public static void setInstance(UserController instance) {
        UserController.instance = instance;
    }

}
