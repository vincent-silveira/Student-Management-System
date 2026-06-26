package application.service;

import application.gui.MainFrame;
import application.gui.StudentFormPanel;
import application.gui.ViewStudentsPanel;
import application.model.FormType;

/**
 * Controls navigation between the application's screens.
 *
 * <p>This class is responsible for displaying the appropriate
 * content panel within the main application window based on
 * user actions.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class NavigationController {

    /** The application's main window. */
    private final MainFrame mainFrame;

    /** Handles student-related operations. */
    private final StudentService studentService;

    /**
     * Creates a navigation controller for the specified main frame.
     *
     * @param mainFrame the application's main window
     */
    public NavigationController(MainFrame mainFrame, StudentService studentService) {
        this.mainFrame = mainFrame;
        this.studentService = studentService;
    }

    /**
     * Displays the form for adding a new student.
     */
    public void navigateToAddStudent() {
        mainFrame.getContentPanel().displayPanel(
                new StudentFormPanel(
                        FormType.ADD,
                        this,
                        studentService
                )
        );
    }

    /**
     * Displays the form for updating an existing student.
     *
     * @param studentRegistrationId the registration ID of the student to update
     */
    public void navigateToUpdateStudent(String studentRegistrationId) {
        mainFrame.getContentPanel().displayPanel(
                new StudentFormPanel(
                        FormType.UPDATE,
                        this,
                        studentService,
                        studentRegistrationId
                )
        );
    }

    /**
     * Displays the panel containing all students.
     */
    public void navigateToViewStudents() {
        mainFrame.getContentPanel().displayPanel(
                new ViewStudentsPanel(
                        this,
                        studentService
                )
        );
    }
}