package db.connection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerPostgeImpl implements ConnectionManager {
    private static final Logger log = Logger.getLogger(ConnectionManagerPostgeImpl.class);
    private static ConnectionManager connectionManager;
    private static String urlDb ="jdbc:postgresql://localhost:5432/Auction";
    private static String nameDb = "postgres";
    private static String paswordDb = "12345678";

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManagerPostgeImpl();
        }
        return connectionManager;
    }

    private ConnectionManagerPostgeImpl() {
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

             connection = DriverManager.getConnection(
                     urlDb,
                    nameDb,
                    paswordDb
            );

        } catch (ClassNotFoundException e) {
            log.error(e.getStackTrace());
        } catch (SQLException e) {
            log.error(e.getStackTrace());
        }
        return connection;
    }
}
