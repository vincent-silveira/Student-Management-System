package application.theme;

import java.awt.*;

/**
 * Stores application-wide layout and UI constants.
 *
 * <p>This utility class centralizes dimensions, font sizes, and layout
 * values used throughout the Student Management System GUI. Keeping these
 * values in one place ensures a consistent user interface and simplifies
 * future modifications.</p>
 *
 * <p>This class cannot be instantiated.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class AppConstants {

    /**
     * Prevents instantiation of this utility class.
     */
    private AppConstants() {
        throw new IllegalStateException("AppConstants class cannot be instantiated");
    }

    // Screen
    /**
     * Width of the primary display in pixels.
     */
    public static final int WINDOW_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    /**
     * Height of the primary display in pixels.
     */
    public static final int WINDOW_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    // Side Bar
    public static final int SIDEBAR_WIDTH = 200;
    public static final int SIDEBAR_HEIGHT = 0;

    // Content
    public static final int CONTENT_WIDTH = WINDOW_WIDTH - SIDEBAR_WIDTH;
    public static final int CONTENT_HEIGHT = WINDOW_HEIGHT;

    // Form
    public static final int FORM_WIDTH = 720;
    public static final int FORM_HEIGHT = 420;

    // Labels
    public static final int FORM_LABEL_WIDTH = 100;
    public static final int FORM_LABEL_HEIGHT = 30;
    public static final int FORM_LABEL_FONT_SIZE = 16;

    // Inputs
    public static final int FORM_INPUT_WIDTH = 400;
    public static final int FORM_INPUT_HEIGHT = 30;
    public static final int FORM_INPUT_FONT_SIZE = 16;

    // Buttons
    public static final int BUTTON_WIDTH = 180;
    public static final int BUTTON_HEIGHT = 30;

    public static final int BUTTON_FONT_SIZE = 17;

    // Title
    public static final int TITLE_HEIGHT = 30;
    public static final int TITLE_FONT_SIZE = 24;

    // Offset
    /**
     * Horizontal offset used when positioning form components.
     */
    public static final int FORM_X_OFFSET = 0;

    /**
     * Vertical offset used when positioning form components.
     */
    public static final int FORM_Y_OFFSET = 80;
}