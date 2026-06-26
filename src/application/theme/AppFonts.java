package application.theme;

import java.awt.*;

/**
 * Stores application-wide font definitions.
 *
 * <p>This utility class provides reusable font instances for
 * buttons, titles, labels, and input fields to ensure a
 * consistent appearance throughout the application.</p>
 *
 * <p>This class cannot be instantiated.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class AppFonts {

    /**
     * Prevents instantiation of this utility class.
     */
    private AppFonts() {
        throw new IllegalStateException("AppFonts class cannot be instantiated");
    }

    /**
     * Font used for application buttons.
     */
    public static final Font BUTTON = new Font(
            Font.SANS_SERIF,
            Font.BOLD,
            AppConstants.BUTTON_FONT_SIZE);

    /**
     * Font used for page titles.
     */
    public static final Font TITLE = new Font(
            Font.SANS_SERIF,
            Font.BOLD,
            AppConstants.TITLE_FONT_SIZE);

    /**
     * Font used for labels.
     */
    public static final Font LABEL = new Font(
            Font.SANS_SERIF,
            Font.BOLD,
            AppConstants.FORM_LABEL_FONT_SIZE);

    /**
     * Font used for form input fields.
     */
    public static final Font INPUT = new Font(
            Font.SANS_SERIF,
            Font.PLAIN,
            AppConstants.FORM_INPUT_FONT_SIZE);
}
