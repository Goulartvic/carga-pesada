package dao;

import connection.ConnectionFactory;

public class RequestDao {

    private ConnectionFactory connectionFactory;

    private static RequestDao instance = new RequestDao();

    public RequestDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public static RequestDao getInstance() {
        return instance;
    }
}
