package control;

import dao.AddressDao;
import model.User;

import java.sql.SQLException;

public class AddressController {

    private static AddressController addressInstance = new AddressController();

    AddressDao addressDao;

    public void saveAddress(User user) {
        addressDao = new AddressDao();
        try {
            addressDao.save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(User user) {
        try {
            addressDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(User user) {
        addressDao = new AddressDao();
        try {
            addressDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAddress(User user) {
        addressDao = new AddressDao();
        try {
            addressDao.setAddress(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static AddressController getAddressInstance() {
        return addressInstance;
    }
}
