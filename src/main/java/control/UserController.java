package control;

import dao.UserDao;
import model.Customer;
import model.User;
import model.UserType;
import model.Worker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController {

    private static UserController instance = new UserController();
    private static User sessionUser;

    public UserController() {
        super();
    }

    public void createAccount(String name, String cpf, String username, String password, String phone, String userType,
                              String city, String number, String state, String street){
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
            UserDao.getInstance().save(user);
            user.setUserId(UserDao.getInstance().lastUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddressController.getAddressInstance().saveAddress(user);
    }

    public void loginUser(String login, String password) {

        try {
            sessionUser = UserDao.getInstance().authenticateUser(login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sessionUser.setAddress(AddressController.getAddressInstance().setAddress(sessionUser));

        if (sessionUser.getUserType() == UserType.WORKER) {
            sessionUser = new Worker(sessionUser.getAddress(), sessionUser.getCpf(), sessionUser.getName(), sessionUser.getPassword(),
                    sessionUser.getPhoneNumber(), sessionUser.getUserId(), sessionUser.getUsername());
        } else {
            sessionUser = new Customer(sessionUser.getAddress(), sessionUser.getCpf(), sessionUser.getName(), sessionUser.getPassword(),
                    sessionUser.getPhoneNumber(), sessionUser.getUserId(), sessionUser.getUsername());
        }
    }

    public boolean userIsValid(String login, String password) {
        try {
            if (UserDao.getInstance().returnAuthentication(login, password)) {
                return true;
            } else {return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void changeUser(User user) {

        try {
            UserDao.getInstance().update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        AddressController.getAddressInstance().updateAddress(user);
    }

    public void deleteUser(){
        AddressController.getAddressInstance().deleteAddress(sessionUser);
        try {
            UserDao.getInstance().delete(UserController.getSessionUser());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> searchAll() {
        List<User> userList = new ArrayList<>();
        try {
            userList = UserDao.getInstance().searchAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean userExist(String login) {
        try {
            return UserDao.getInstance().userExist(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
