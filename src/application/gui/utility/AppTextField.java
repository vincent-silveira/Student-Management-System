package application.gui.utility;

import application.theme.AppConstants;
import application.theme.AppFonts;
import application.theme.AppTheme;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * A reusable text field component that provides a consistent appearance
 * throughout the application.
 *
 * <p>The text field automatically applies the application's font,
 * colors, border, and caret styling.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class AppTextField extends JTextField {

    /**
     * Creates an empty styled text field.
     */
    public AppTextField() {
        initializeTextField();
    }

    /**
     * Creates a styled text field containing the specified text.
     *
     * @param text the initial text displayed in the text field
     */
    public AppTextField(String text) {
        super(text);
        initializeTextField();
    }

    /**
     * Creates a styled text field positioned at the specified coordinates.
     *
     * @param text the initial text displayed in the text field
     * @param x the x-coordinate of the text field
     * @param y the y-coordinate of the text field
     */
    public AppTextField(String text, int x, int y) {
        this(text);
        setBounds(x, y);
    }

    /**
     * Initializes the appearance of the text field.
     */
    private void initializeTextField() {
        setFont(AppFonts.INPUT);
        setForeground(AppTheme.INPUT_FOREGROUND);
        setBackground(AppTheme.INPUT_BACKGROUND);
        setCaretColor(AppTheme.INPUT_FOREGROUND);

        setBorder(new CompoundBorder(
                new LineBorder(AppTheme.INPUT_BORDER, 1, true),
                new EmptyBorder(6, 10, 6, 10)
        ));
    }

    /**
     * Sets the text field's position while preserving its predefined
     * dimensions.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setBounds(int x, int y) {
        setBounds(
                x,
                y,
                AppConstants.FORM_INPUT_WIDTH,
                AppConstants.FORM_INPUT_HEIGHT
        );
    }
}