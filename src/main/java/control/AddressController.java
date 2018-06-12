package control;

import dao.AddressDao;
import model.Address;
import model.User;

import java.sql.SQLException;

public class AddressController {

    private static AddressController addressInstance = new AddressController();

    public void saveAddress(User user) {
        try {
            AddressDao.getInstance().save(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAddress(User user) {
        try {
            AddressDao.getInstance().delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAddress(User user) {
        try {
            AddressDao.getInstance().update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Address setAddress(User user) {
        Address address = new Address();
        try {
            address = AddressDao.getInstance().setAddress(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public static AddressController getAddressInstance() {
        return addressInstance;
    }
}
