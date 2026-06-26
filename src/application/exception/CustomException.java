package application.exception;

import javax.swing.*;

/**
 * Base class for all custom application exceptions.
 *
 * <p>This class stores an error title and provides a common
 * mechanism for displaying error messages using a dialog box.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public abstract class CustomException extends RuntimeException {

    private final String title;

    /**
     * Creates a custom application.exception with the specified title and message.
     *
     * @param title the dialog title
     * @param message the application.exception message
     */
    public CustomException(String title, String message) {
        super(message);
        this.title = title;
    }

    /**
     * Displays the application.exception message in an error dialog.
     */
    public void handleException() {
            JOptionPane.showMessageDialog(
                    null,
                    this.getMessage(),
                    this.getTitle(),
                    JOptionPane.ERROR_MESSAGE
            );
    }

    /**
     * Returns the application.exception title.
     *
     * @return the application.exception title
     */
    public final String getTitle(){
        return this.title;
    }
}