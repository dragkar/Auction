package db.connection;

import java.sql.Connection;

public interface ConnectionManager {

    Connection getConnection();
}
