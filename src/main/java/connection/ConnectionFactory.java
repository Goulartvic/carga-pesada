package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public ConnectionFactory() {
        connection();
    }

    public Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cargapesada", "root", "root");

            return connection;
        } catch (ClassNotFoundException|SQLException e) {
            System.err.println("error:" + e);
            return null;
        }
    }
}
