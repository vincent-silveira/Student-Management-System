package application.gui;

import application.config.ConnectionConfig;
import application.gui.utility.AppButton;
import application.gui.utility.ButtonType;
import application.service.NavigationController;
import application.theme.AppConstants;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the application's navigation sidebar.
 *
 * <p>This panel provides the primary navigation controls for the
 * application. User actions are delegated to the
 * {@link NavigationController}, which updates the application's
 * displayed screen.</p>
 *
 * <p>The sidebar also provides an option to safely close the
 * application by releasing database resources before exiting.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class SidebarPanel extends JPanel {

    /** Horizontal position of sidebar buttons. */
    private static final int BUTTON_X = 10;

    /** Vertical position of the Add Student button. */
    private static final int ADD_STUDENT_BUTTON_Y = 90;

    /** Vertical position of the View Students button. */
    private static final int VIEW_STUDENTS_BUTTON_Y = 140;

    /** Distance of the Close button from the bottom of the window. */
    private static final int CLOSE_BUTTON_OFFSET = 130;

    /** Handles navigation between application screens. */
    private final NavigationController navigationController;

    /** Button used to navigate to the Add Student screen. */
    private final AppButton addStudentButton;

    /** Button used to navigate to the View Students screen. */
    private final AppButton viewStudentsButton;

    /** Button used to close the application. */
    private final AppButton closeButton;

    /**
     * Creates the application's navigation sidebar.
     *
     * @param navigationController the application's navigation controller
     */
    public SidebarPanel(NavigationController navigationController){
        this.navigationController = navigationController;

        this.addStudentButton = new AppButton("Add Students", ButtonType.NAVIGATION);
        this.viewStudentsButton = new AppButton("View Students", ButtonType.NAVIGATION);
        this.closeButton = new AppButton("Close", ButtonType.DANGER);

        initializePanel();
        initializeComponents();
        initializeActionListeners();
    }

    /**
     * Initializes the sidebar layout and appearance.
     */
    private void initializePanel(){

        this.setPreferredSize(new Dimension(
                        AppConstants.SIDEBAR_WIDTH,
                        AppConstants.SIDEBAR_HEIGHT
                )
        );

        this.setBackground(AppTheme.SIDEBAR_BACKGROUND);

        this.setLayout(null);
    }

    /**
     * Creates and positions the sidebar buttons.
     */
    private void initializeComponents() {
        addButton(addStudentButton, ADD_STUDENT_BUTTON_Y);
        addButton(viewStudentsButton, VIEW_STUDENTS_BUTTON_Y);
        addButton(closeButton, AppConstants.WINDOW_HEIGHT - CLOSE_BUTTON_OFFSET);
    }

    /**
     * Adds a button to the sidebar at the specified vertical position.
     *
     * @param button the button to add
     * @param y the y-coordinate of the button
     */
    private void addButton(AppButton button, int y){
        button.setBounds(BUTTON_X, y);
        add(button);
    }

    /**
     * Registers the action listeners for the sidebar buttons.
     */
    private void initializeActionListeners(){
        addStudentButton.addActionListener(e -> onAddStudent());
        viewStudentsButton.addActionListener(e-> onViewStudents());
        closeButton.addActionListener(e -> onClose());
    }

    /**
     * Navigates to the Add Student screen.
     */
    private void onAddStudent(){
        navigationController.navigateToAddStudent();
    }

    /**
     * Navigates to the View Students screen.
     */
    private void onViewStudents(){
        navigationController.navigateToViewStudents();
    }

    /**
     * Closes the application's database connection and terminates
     * the application.
     */
    private void onClose(){
        ConnectionConfig.closeConnection();
        System.exit(0);
    }
}
