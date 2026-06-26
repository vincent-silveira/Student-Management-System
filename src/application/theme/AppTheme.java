package application.theme;

import java.awt.*;

/**
 * Stores the application's color palette.
 *
 * <p>This utility class provides reusable color definitions for
 * panels, buttons, input fields, and other user interface components.
 * Centralizing these values ensures a consistent visual application.theme and
 * simplifies future modifications.</p>
 *
 * <p>This class cannot be instantiated.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class AppTheme {

    /**
     * Prevents instantiation of this utility class.
     */
    private AppTheme() {
        throw new IllegalStateException("AppTheme class cannot be instantiated");
    }

    /** Background color of the main content panel. */
    public static final Color CONTENT_BACKGROUND = new Color(245, 245, 245);

    /** Background color of the sidebar. */
    public static final Color SIDEBAR_BACKGROUND = new Color(44, 62, 80);

    /** Background color of forms. */
    public static final Color FORM_BACKGROUND = new Color(245, 247, 250);

    /** Background color of sidebar buttons. */
    public static final Color SIDEBAR_BUTTON_BACKGROUND = new Color(55, 74, 95);

    /** Background color of sidebar buttons when hovered. */
    public static final Color SIDEBAR_BUTTON_HOVER = new Color(68, 89, 110);

    /** Text color of sidebar buttons. */
    public static final Color SIDEBAR_BUTTON_FOREGROUND = Color.WHITE;

    /** Border color of sidebar buttons. */
    public static final Color SIDEBAR_BUTTON_BORDER = new Color(62, 82, 102);

    /** Background color of action buttons. */
    public static final Color ACTION_BUTTON_BACKGROUND = new Color(46, 204, 113);

    /** Background color of action buttons when hovered. */
    public static final Color ACTION_BUTTON_HOVER = new Color(39, 174, 96);

    /** Text color of action buttons. */
    public static final Color ACTION_BUTTON_FOREGROUND = Color.WHITE;

    /** Border color of action buttons. */
    public static final Color ACTION_BUTTON_BORDER = new Color(30, 132, 73);

    /** Background color of danger buttons. */
    public static final Color DANGER_BUTTON_BACKGROUND = new Color(231, 76, 60);

    /** Background color of danger buttons when hovered. */
    public static final Color DANGER_BUTTON_HOVER = new Color(192, 57, 43);

    /** Text color of danger buttons. */
    public static final Color DANGER_BUTTON_FOREGROUND = Color.WHITE;

    /** Border color of danger buttons. */
    public static final Color DANGER_BUTTON_BORDER = new Color(160, 45, 35);

    /** Background color of input fields. */
    public static final Color INPUT_BACKGROUND = Color.WHITE;

    /** Text color of input fields. */
    public static final Color INPUT_FOREGROUND = Color.BLACK;

    /** Border color of input fields. */
    public static final Color INPUT_BORDER = new Color(180, 180, 180);

}
