package application.config;

import application.exception.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides database connections for DAO classes.
 *
 * <p>Connection details are loaded from {@link DatabaseProperties}
 * and a JDBC connection is created when {@link #getConnection()}
 * is called.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class ConnectionConfig {

    /**
     * Reference to the current JDBC connection.
     */
    public static  Connection connection;

    /**
     * Creates and returns a JDBC connection.
     *
     * <p>The connection details are read from {@link DatabaseProperties}.
     * If a connection cannot be established, a {@link DatabaseException}
     * is thrown.</p>
     *
     * @return an active database connection
     * @throws DatabaseException if the connection cannot be established
     */
    public static Connection getConnection() throws DatabaseException {
            try {
                String url = DatabaseProperties.URL;
                String username = DatabaseProperties.USERNAME;
                String password = DatabaseProperties.PASSWORD;
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                throw new DatabaseException(
                        "Database Connection Failed",
                        "Unable to connect to the database.\nPlease check that the database server is running and your credentials are correct."
                );
            }
            return connection;
    }

    /**
     * Closes the active database connection if one exists.
     *
     * <p>This method should be called during application shutdown
     * to release database resources.</p>
     *
     * @throws DatabaseException if the connection cannot be closed
     */
    public static void closeConnection() throws DatabaseException {
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e){
            throw new DatabaseException(
                    "Database Close Failed",
                    "The system could not safely close the database connection.");
        }
    }
}
