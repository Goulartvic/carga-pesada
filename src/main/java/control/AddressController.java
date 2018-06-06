package control;

import dao.AddressDao;
import model.User;

public class AddressController {

    private static AddressController addressInstance = new AddressController();

    AddressDao addressDao;

    public void saveAddress(User user) {
        addressDao = new AddressDao();
        addressDao.save(user);
    }

    public void deleteAddress(User user) {}

    public void updateAddress(User user) {
        addressDao = new AddressDao();
        addressDao.update(user);
    }

    public void setAddress(User user) {
        addressDao = new AddressDao();
        addressDao.setAddress(user);
    }

    public static AddressController getAddressInstance() {
        return addressInstance;
    }
}
