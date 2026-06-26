package application.gui.utility;

import application.theme.AppConstants;
import application.theme.AppFonts;

import javax.swing.*;
import java.awt.*;

/**
 * A reusable label component that provides a consistent appearance
 * throughout the application.
 *
 * <p>The label automatically applies a predefined style based on the
 * specified {@link LabelType}, including font, dimensions, and text
 * alignment.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class AppLabel extends JLabel {

    /** Width of the label. */
    private int width;

    /** Height of the label. */
    private int height;

    /**
     * Creates a styled label using the specified label type.
     *
     * @param text the text displayed by the label
     * @param type the predefined label style
     */
    public AppLabel(String text, LabelType type) {
        super(text);

        switch (type) {
            case FORM_TITLE -> styleFormTitle();
            case DISPLAY_TITLE -> styleDisplayTitle();
            case FORM -> styleForm();
            case CUSTOM -> styleCustom();
        }
    }

    /**
     * Creates a styled label positioned at the specified coordinates.
     *
     * @param text the text displayed by the label
     * @param type the predefined label style
     * @param x the x-coordinate of the label
     * @param y the y-coordinate of the label
     */
    public AppLabel(String text, LabelType type, int x, int y) {
        this(text, type);
        setBounds(x, y);
    }

    /**
     * Applies the form title label style.
     */
    private void styleFormTitle() {
        width = AppConstants.FORM_WIDTH;
        height = AppConstants.TITLE_HEIGHT;

        setFont(AppFonts.TITLE);
        setHorizontalAlignment(CENTER);
    }

    /**
     * Applies the display title label style.
     */
    private void styleDisplayTitle() {
        width = AppConstants.CONTENT_WIDTH;
        height = AppConstants.TITLE_HEIGHT;

        setFont(AppFonts.TITLE);
        setHorizontalAlignment(CENTER);
    }

    /**
     * Applies the standard form label style.
     */
    private void styleForm() {
        width = AppConstants.FORM_LABEL_WIDTH;
        height = AppConstants.FORM_LABEL_HEIGHT;

        setFont(AppFonts.LABEL);
    }

    /**
     * Applies the custom label style.
     */
    private void styleCustom() {
        setFont(AppFonts.LABEL);
    }

    /**
     * Sets the label's position while preserving its configured dimensions.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setBounds(int x, int y) {
        setBounds(x, y, width, height);
    }

    /**
     * Sets custom dimensions for the label.
     *
     * <p>The preferred, minimum, and maximum sizes are updated to
     * the specified dimensions.</p>
     *
     * @param width the desired width
     * @param height the desired height
     */
    public void setCustomSize(int width, int height) {
        Dimension dimension = new Dimension(width, height);

        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }
}