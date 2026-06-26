package application.gui;

import application.service.NavigationController;
import application.service.StudentService;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main application window for the Student Management System.
 *
 * <p>This frame serves as the root container for the application's user
 * interface. It contains a single {@link SidebarPanel} used for navigation
 * and a single {@link ContentPanel} that displays the currently active
 * application screen.</p>
 *
 * <p>The {@code ContentPanel} instance is created once during application
 * startup and reused throughout the application's lifetime. Navigation
 * changes only the displayed content without replacing the content panel
 * itself.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class MainFrame extends JFrame {

    /**
     * Sidebar containing the application's navigation controls.
     */
    private final SidebarPanel sidebarPanel;

    /**
     * Central content area used to display application screens.
     */
    private final ContentPanel contentPanel;

    /**
     * Creates and initializes the main application window.
     *
     * <p>This constructor creates the shared application services,
     * initializes the navigation system, constructs the sidebar and
     * content panels, and adds them to the frame.</p>
     */
    public MainFrame() {
        initializeFrame();

        final StudentService studentService = new StudentService();
        final NavigationController navigationController =
                new NavigationController(this, studentService);

        sidebarPanel = new SidebarPanel(navigationController);
        contentPanel = new ContentPanel(
                new ViewStudentsPanel(navigationController, studentService)
        );

        addComponents();
    }

    /**
     * Configures the frame's basic properties.
     *
     * <p>The frame is given a title, configured to terminate the
     * application when closed, and opened in a maximized state.</p>
     */
    private void initializeFrame() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Adds the main user interface components to the frame.
     *
     * <p>The sidebar is placed on the left side of the window, while
     * the content panel occupies the remaining available space.</p>
     */
    private void addComponents() {
        add(sidebarPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
    }

    /**
     * Returns the application's shared content panel.
     *
     * <p>The returned instance is reused throughout the application's
     * lifetime to display different screens.</p>
     *
     * @return the shared content panel.
     */
    public ContentPanel getContentPanel() {
        return contentPanel;
    }
}