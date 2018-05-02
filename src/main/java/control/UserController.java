package control;

import model.User;

public class UserController {

    private static UserController userController;
    private static User sessionUser;

    public UserController() {
        super();
    }

    public void createAccount(){

    }

    public void deleteUser(){

    }

    public boolean userExist() {
        return false;
    }

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        // Usar para setar o usuário da sessão após autenticação pelo login
        userController.sessionUser = sessionUser;
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public static void setUserController(UserController userController) {
        UserController.userController = userController;
    }

}
