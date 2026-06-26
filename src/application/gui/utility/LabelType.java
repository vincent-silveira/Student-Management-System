package application.gui.utility;

/**
 * Defines the predefined styles available for {@link AppLabel}.
 *
 * <p>Each label type determines the label's font, dimensions,
 * and text alignment.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public enum LabelType {

    /** Large title displayed at the top of content panels. */
    DISPLAY_TITLE,

    /** Large title displayed at the top of forms. */
    FORM_TITLE,

    /** Standard label used to identify form input fields. */
    FORM,

    /** Label with customizable dimensions and placement. */
    CUSTOM
}