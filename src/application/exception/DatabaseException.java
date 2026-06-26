package application.exception;

/**
 * Exception thrown when a database operation fails.
 *
 * <p>This application.exception represents errors that occur while
 * performing database operations such as retrieving,
 * inserting, updating, or deleting student records.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class DatabaseException extends CustomException {

    /**
     * Creates a database application.exception with the specified title and message.
     *
     * @param title the dialog title
     * @param message the application.exception message
     */
    public DatabaseException(String title, String message) {
        super(title, message);
    }
}