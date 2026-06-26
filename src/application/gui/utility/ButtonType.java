package application.gui.utility;

/**
 * Defines the predefined styles available for {@link AppButton}.
 *
 * <p>Each button type determines the button's colors, border,
 * and overall appearance.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public enum ButtonType {

    /** Navigation button used within the application's sidebar. */
    NAVIGATION,

    /** Primary action button used to perform standard operations. */
    ACTION,

    /** Danger button used for destructive operations such as deletion or cancellation. */
    DANGER
}