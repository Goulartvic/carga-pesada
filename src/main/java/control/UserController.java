package control;

import model.User;

public class UserController {

    private static UserController userController;
    private static User sessionUser;

    public UserController() {
        super();
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

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        userController.sessionUser = sessionUser;
    }

}
