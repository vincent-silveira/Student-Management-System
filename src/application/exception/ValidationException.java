package application.exception;

/**
 * Base class for exceptions caused by invalid user input.
 *
 * <p>This application.exception represents validation failures that occur when
 * user-supplied data does not satisfy the application's validation
 * rules.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class ValidationException extends CustomException {

    /**
     * Creates a validation application.exception with the specified title and message.
     *
     * @param title the dialog title
     * @param message the application.exception message
     */
    public ValidationException(String title, String message) {
        super(title, message);
    }
}