package application.config;

/**
 * Stores database connection configuration values used by the application.
 *
 * <p>This class contains the JDBC URL, database username, and password
 * required to establish a connection with the MySQL database.</p>
 *
 * <p>This class is not intended to be instantiated. It only provides
 * constant configuration values.</p>
 *
 * @author Vincent
 * @version 1.0
 */
public final class DatabaseProperties {
    /**
     * JDBC connection URL for the student management database.
     */
    public final static String URL = "jdbc:mysql://localhost:3306/student_db";

    /**
     * Database username used for authentication.
     */
    public final static String USERNAME = "root";

    /**
     * Database password used for authentication.
     */
    public final static String PASSWORD = "1234";

    /**
     * Private constructor to prevent object creation.
     */
    private DatabaseProperties() {
        throw new IllegalStateException("DatabaseProperties class cannot be instantiated");
    }
}
