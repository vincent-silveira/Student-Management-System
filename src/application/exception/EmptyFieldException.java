package application.exception;

/**
 * Exception thrown when a required input field is left empty.
 *
 * <p>This application.exception is used when the user fails to provide
 * a value for a mandatory field.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class EmptyFieldException extends ValidationException {

    /**
     * Creates an application.exception for a missing required field.
     *
     * @param inputField the name of the required field
     */
    public EmptyFieldException(String inputField) {
        super(
                "Missing Required Field",
                inputField + " is required."
        );
    }
}