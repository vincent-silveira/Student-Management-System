package application.gui.utility;

import application.theme.AppConstants;
import application.theme.AppFonts;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A reusable button component that provides a consistent appearance
 * throughout the application.
 *
 * <p>The button automatically applies a predefined style based on the
 * specified {@link ButtonType}, including colors, font, border,
 * dimensions, and hover effects.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class AppButton extends JButton {

    /** Default background color of the button. */
    private Color defaultBackground;

    /** Background color displayed when the mouse hovers over the button. */
    private Color hoverBackground;

    /** Border color of the button. */
    private Color borderColor;

    /** Foreground (text) color of the button. */
    private Color foregroundColor;

    /** Width of the button. */
    private int width;

    /** Height of the button. */
    private int height;

    /** Font used to display the button text. */
    private Font font;

    /**
     * Creates a styled button using the specified button type.
     *
     * @param text the text displayed on the button
     * @param type the predefined button style
     */
    public AppButton(String text, ButtonType type) {
        super(text);

        initializeButton();

        switch (type) {
            case NAVIGATION -> styleNavigation();
            case ACTION -> styleAction();
            case DANGER -> styleDanger();
        }

        initializeSize();
        applyAppearance();
        initializeBorder();
        addHoverEffect();
    }

    /**
     * Creates a styled button positioned at the specified coordinates.
     *
     * @param text the text displayed on the button
     * @param type the predefined button style
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     */
    public AppButton(String text, ButtonType type, int x, int y) {
        this(text, type);
        setBounds(x, y);
    }

    /**
     * Initializes the button with common properties shared by all button
     * styles.
     */
    private void initializeButton() {
        setFocusPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Applies the navigation button style.
     */
    private void styleNavigation() {
        defaultBackground = AppTheme.SIDEBAR_BUTTON_BACKGROUND;
        hoverBackground = AppTheme.SIDEBAR_BUTTON_HOVER;
        borderColor = AppTheme.SIDEBAR_BUTTON_BORDER;
        foregroundColor = AppTheme.SIDEBAR_BUTTON_FOREGROUND;

        width = AppConstants.BUTTON_WIDTH;
        height = AppConstants.BUTTON_HEIGHT;

        font = AppFonts.BUTTON;

        setHorizontalAlignment(SwingConstants.LEFT);
    }

    /**
     * Applies the action button style.
     */
    private void styleAction() {
        defaultBackground = AppTheme.ACTION_BUTTON_BACKGROUND;
        hoverBackground = AppTheme.ACTION_BUTTON_HOVER;
        borderColor = AppTheme.ACTION_BUTTON_BORDER;
        foregroundColor = AppTheme.ACTION_BUTTON_FOREGROUND;

        width = AppConstants.BUTTON_WIDTH;
        height = AppConstants.BUTTON_HEIGHT;

        font = AppFonts.BUTTON;
    }

    /**
     * Applies the danger button style.
     */
    private void styleDanger() {
        defaultBackground = AppTheme.DANGER_BUTTON_BACKGROUND;
        hoverBackground = AppTheme.DANGER_BUTTON_HOVER;
        borderColor = AppTheme.DANGER_BUTTON_BORDER;
        foregroundColor = AppTheme.DANGER_BUTTON_FOREGROUND;

        width = AppConstants.BUTTON_WIDTH;
        height = AppConstants.BUTTON_HEIGHT;

        font = AppFonts.BUTTON;
    }

    /**
     * Initializes the button dimensions and applies them as the preferred,
     * minimum, maximum, and current size.
     */
    private void initializeSize() {
        Dimension dimension = new Dimension(width, height);

        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
    }

    /**
     * Applies the configured background color, foreground color, and font
     * to the button.
     */
    private void applyAppearance() {
        setBackground(defaultBackground);
        setForeground(foregroundColor);
        setFont(font);
    }

    /**
     * Creates and applies the button border.
     */
    private void initializeBorder() {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                BorderFactory.createEmptyBorder(12, 20, 12, 20)
        ));
    }

    /**
     * Sets the button's location while preserving its configured dimensions.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    public void setBounds(int x, int y) {
        setBounds(x, y, width, height);
    }

    /**
     * Adds a hover effect that changes the button's background color when
     * the mouse pointer enters or exits the button.
     */
    private void addHoverEffect() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackground);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(defaultBackground);
            }
        });
    }
}