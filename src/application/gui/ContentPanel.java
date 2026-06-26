package application.gui;

import application.theme.AppConstants;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main content area of the application window.
 *
 * <p>A single instance of this panel is created when the application
 * starts and is reused throughout its lifetime. Rather than replacing
 * the panel itself, the displayed application screen is changed using
 * {@link #displayPanel(JPanel)}.</p>
 *
 * <p>Panels that require the full available space, such as
 * {@link ViewStudentsPanel}, are displayed directly, while all other
 * panels are automatically centered within the content area.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class ContentPanel extends JPanel {

    /**
     * Creates the content panel and displays the initial screen.
     *
     * @param initialPanel
     *        the initial panel to display.
     */
    public ContentPanel(JPanel initialPanel) {
        initializePanel();
        displayPanel(initialPanel);
    }

    /**
     * Initializes the appearance and layout of the content panel.
     *
     * <p>The panel uses a {@link BorderLayout} and is configured with
     * the application's standard content dimensions and background
     * color.</p>
     */
    private void initializePanel() {
        setPreferredSize(new Dimension(
                AppConstants.CONTENT_WIDTH,
                AppConstants.CONTENT_HEIGHT
        ));
        setLayout(new BorderLayout());
        setBackground(AppTheme.CONTENT_BACKGROUND);
    }

    /**
     * Displays the specified panel within the content area.
     *
     * <p>If the supplied panel is a {@link ViewStudentsPanel}, it is
     * displayed using the full available space. All other panels are
     * centered both horizontally and vertically using a transparent
     * container panel.</p>
     *
     * @param panel
     *        the panel to display.
     */
    public void displayPanel(JPanel panel) {
        removeAll();

        if (panel instanceof ViewStudentsPanel) {
            add(panel, BorderLayout.CENTER);
        } else {
            final JPanel centerPanel = new JPanel(new GridBagLayout());
            centerPanel.setOpaque(false);
            centerPanel.add(panel);

            add(centerPanel, BorderLayout.CENTER);
        }

        revalidate();
        repaint();
    }
}