package application.exception;

/**
 * Exception thrown when a user enters a value that is
 * outside the allowed range or violates a validation rule.
 *
 * <p>This application.exception is used when the input type is valid
 * but the value itself is not acceptable.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class InvalidInputValueException extends ValidationException {

    /**
     * Creates an application.exception for an invalid input value.
     *
     * @param field the name of the invalid field
     * @param reason the reason why the value is invalid
     */
    public InvalidInputValueException(String field, String reason) {
        super(
                "Invalid " + field,
                reason
        );
    }
}