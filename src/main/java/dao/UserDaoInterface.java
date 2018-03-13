package dao;

import model.User;

import java.util.List;

public interface UserDaoInterface extends DAO<User>{

    List<User> searchAll();
    User authenticateUser(String email, String password);
}
