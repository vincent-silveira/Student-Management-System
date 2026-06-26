package application.gui;

import application.exception.CustomException;
import application.gui.utility.AppButton;
import application.gui.utility.AppLabel;
import application.gui.utility.ButtonType;
import application.gui.utility.LabelType;
import application.model.Student;
import application.service.NavigationController;
import application.service.StudentService;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a single student's information within the student list.
 *
 * <p>Each instance represents one student record and provides
 * actions to update or delete that student. User interactions
 * are delegated to the application's navigation and application.service
 * layers.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class StudentRowPanel extends JPanel {

    /** Preferred width of the roll number label. */
    private static final int ROLL_NUMBER_WIDTH = 70;

    /** Preferred width of the student name label. */
    private static final int NAME_WIDTH = 200;

    /** Preferred width of the standard label. */
    private static final int STANDARD_WIDTH = 60;

    /** Preferred width of the division label. */
    private static final int DIVISION_WIDTH = 60;

    /** Preferred height of each information label. */
    private static final int LABEL_HEIGHT = 30;

    /** Handles navigation between application screens. */
    private final NavigationController navigationController;

    /** Handles student-related operations. */
    private final StudentService studentService;

    /** Student represented by this panel. */
    private final Student student;

    /** Button used to update the student. */
    private final AppButton updateButton;

    /** Button used to delete the student. */
    private final AppButton deleteButton;

    /**
     * Creates a panel displaying the supplied student's information.
     *
     * @param navigationController
     *        the application's navigation controller.
     * @param studentService
     *        application.service used to perform student operations.
     * @param student
     *        the student displayed by this panel.
     */
    public StudentRowPanel(NavigationController navigationController, StudentService studentService, Student student) {
        this.navigationController = navigationController;
        this.studentService = studentService;
        this.student = student;

        this.updateButton = new AppButton("UPDATE", ButtonType.ACTION);
        this.deleteButton = new AppButton("DELETE", ButtonType.DANGER);

        initializePanel();
        initializeComponents();
        initializeActionListeners();
    }

    /**
     * Initializes the appearance of the panel.
     */
    private void initializePanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(AppTheme.FORM_BACKGROUND);
        setOpaque(true);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(14, 20, 14, 20)
        ));
    }

    /**
     * Creates and adds all labels and action buttons.
     */
    private void initializeComponents() {
        initializeLabels();
        initializeButtons();
    }

    /**
     * Creates and adds labels displaying the student's information.
     */
    private void initializeLabels() {
        addStudentLabel(String.valueOf(student.getRollNo()), ROLL_NUMBER_WIDTH);
        addStudentLabel(student.getName(), NAME_WIDTH);
        addStudentLabel(String.valueOf(student.getStandard()), STANDARD_WIDTH);
        addStudentLabel(String.valueOf(student.getDivision()), DIVISION_WIDTH);
    }

    /**
     * Adds the update and delete action buttons.
     */
    private void initializeButtons() {
        addButton(updateButton);
        addButton(deleteButton);
    }

    /**
     * Registers the action listeners for the panel buttons.
     */
    private void initializeActionListeners() {
        updateButton.addActionListener(e -> onUpdate());
        deleteButton.addActionListener(e -> onDelete());
    }

    /**
     * Adds a student information label to the panel.
     *
     * @param text the label text
     * @param width the preferred label width
     */
    private void addStudentLabel(String text, int width) {
        AppLabel label = new AppLabel(text, LabelType.CUSTOM);
        label.setCustomSize(width, LABEL_HEIGHT);

        add(label);
        add(Box.createHorizontalStrut(10));
    }

    /**
     * Adds a button to the panel followed by spacing.
     *
     * @param button the button to add
     */
    private void addButton(AppButton button) {
        add(button);
        add(Box.createHorizontalStrut(10));
    }

    /**
     * Opens the update screen for the current student.
     */
    private void onUpdate() {
        navigationController.navigateToUpdateStudent(student.getRegistrationId());
    }

    /**
     * Confirms and deletes the current student.
     *
     * <p>If the user confirms the operation, the student is
     * deleted and the student list is refreshed.</p>
     */
    private void onDelete() {

        final int option = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to delete this student?",
                "Delete Student",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (option != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            final boolean success =
                    studentService.deleteStudent(student.getRegistrationId());

            if (success) {
                JOptionPane.showMessageDialog(
                        this,
                        "The student has been deleted successfully.",
                        "Student Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );

                navigationController.navigateToViewStudents();
            }

        } catch (CustomException e) {
            e.handleException();
        }
    }
}