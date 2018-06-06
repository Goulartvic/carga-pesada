package dao;

import connection.ConnectionFactory;
import model.Worker;

public class VehicleDao {

    private ConnectionFactory connectionFactory;

    public VehicleDao() {this.connectionFactory = new ConnectionFactory();}

    public void save(Worker worker) {}

    public void delete(Worker worker) {}

    public void update(Worker worker) {}
}
