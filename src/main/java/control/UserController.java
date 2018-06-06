package control;

import dao.AddressDao;
import dao.UserDao;
import model.User;
import model.UserType;

public class UserController {

    private static UserController instance = new UserController();
    private static User sessionUser;
    private UserDao userDao;
    private AddressDao addressDao;

    public UserController() {
        super();
    }

    public void createAccount(String name, String cpf, String username, String password, String phone, String userType,
                              String city, String number, String state, String street){
        userDao = new UserDao();
        addressDao = new AddressDao();
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
        userDao.save(user);
        user.setUserId(userDao.lastUserId());
        addressDao.save(user);
    }

    public void loginUser(String login, String password) {
        userDao = new UserDao();
        addressDao = new AddressDao();

        sessionUser = userDao.authenticateUser(login, password);
        addressDao.setAddress(sessionUser);
    }

    public boolean userIsValid(String login, String password) {
        userDao = new UserDao();
        if (userDao.returnAuthentication(login, password)) {
            return true;
        } else {return false;}
    }

    public void changeUser(User user) {
        userDao = new UserDao();
        addressDao = new AddressDao();

        userDao.update(user);
        addressDao.update(user);
    }

    public void deleteUser(){
        userDao = new UserDao();
        addressDao = new AddressDao();
        addressDao.delete(UserController.getSessionUser());
        userDao.delete(UserController.getSessionUser());
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
