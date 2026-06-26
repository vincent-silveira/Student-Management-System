package application.exception;

/**
 * Exception thrown when a user enters data in an invalid type or format.
 *
 * <p>This application.exception is used when the supplied input cannot be converted
 * to the expected data type or does not match the required format.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class InvalidInputTypeException extends ValidationException {

    /**
     * Creates an application.exception for an invalid input type.
     *
     * @param field the name of the field containing invalid input
     * @param reason the reason the input is considered invalid
     */
    public InvalidInputTypeException(String field, String reason) {
        super(
                "Invalid Type or Format",
                field + ": " + reason
        );
    }
}
