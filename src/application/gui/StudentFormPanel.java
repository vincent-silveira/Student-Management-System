package application.gui;

import application.exception.CustomException;
import application.exception.DatabaseException;
import application.gui.utility.*;
import application.model.FormType;
import application.model.Student;
import application.service.NavigationController;
import application.service.StudentService;
import application.theme.AppConstants;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a form used to create or update student records.
 *
 * <p>This panel provides a reusable form for entering student
 * information. Depending on the supplied {@link FormType},
 * it either creates a new student or updates an existing one.</p>
 *
 * <p>When used for updates, the form is automatically populated
 * with the student's existing information before editing.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class StudentFormPanel extends JPanel {

    /** Horizontal position of form labels. */
    private static final int LABEL_X = 100;

    /** Horizontal position of input fields. */
    private static final int INPUT_FIELD_X = 220;

    /** Vertical position of the roll number field. */
    private static final int ROLL_NUMBER_Y = 100;

    /** Vertical position of the name field. */
    private static final int NAME_Y = 140;

    /** Vertical position of the standard field. */
    private static final int STANDARD_Y = 180;

    /** Vertical position of the division field. */
    private static final int DIVISION_Y = 220;

    /** Vertical position of the action buttons. */
    private static final int BUTTON_Y = 270;

    /** Horizontal position of the cancel button. */
    private static final int CANCEL_BUTTON_X = 220;

    /** Horizontal position of the confirm button. */
    private static final int CONFIRM_BUTTON_X = 440;

    /** Determines whether the form is used to add or update a student. */
    private final FormType formType;

    /** Handles navigation between application screens. */
    private final NavigationController navigationController;

    /** Handles student-related operations. */
    private final StudentService studentService;

    /** Registration ID of the student associated with this form when updating. */
    private String registrationId;

    /** Text field for the student's roll number. */
    private final AppTextField rollNoTextField;

    /** Text field for the student's name. */
    private final AppTextField nameTextField;

    /** Text field for the student's standard. */
    private final AppTextField standardTextField;

    /** Text field for the student's division. */
    private final AppTextField divisionTextField;

    /** Button used to submit the form. */
    private final AppButton confirmButton;

    /** Button used to clear the form fields. */
    private final AppButton cancelButton;

    /**
     * Creates a student form for the specified operation.
     *
     * @param formType specifies whether the form is used to add or update a student
     * @param navigationController the application's navigation controller
     * @param studentService application.service used to perform student operations.
     */
    public StudentFormPanel(FormType formType, NavigationController navigationController, StudentService studentService){
        this.formType = formType;
        this.navigationController = navigationController;
        this.studentService = studentService;

        this.rollNoTextField = new AppTextField();
        this.nameTextField = new AppTextField();
        this.standardTextField = new AppTextField();
        this.divisionTextField = new AppTextField();

        this.confirmButton = new AppButton(formType.toString().toUpperCase(), ButtonType.ACTION);
        this.cancelButton = new AppButton("CANCEL", ButtonType.DANGER);

        initializePanel();
        initializeComponents();
        initializeActionListeners();
    }

    /**
     * Creates a student form pre-populated with an existing student's data.
     *
     * @param formType specifies the form operation
     * @param navigationController the application's navigation controller
     * @param studentService application.service used to perform student operations.
     * @param registrationId the registration ID of the student to update
     */
    public StudentFormPanel(FormType formType, NavigationController navigationController, StudentService studentService, String registrationId){
        this(formType, navigationController, studentService);
        this.registrationId = registrationId;
        fillStudentDetails(registrationId);
    }

    /**
     * Retrieves the specified student's information and populates
     * the form fields.
     *
     * @param registrationId
     *        registration ID of the student being edited.
     */
    private void fillStudentDetails(String registrationId) {
        try {
            Student student = studentService.getStudent(registrationId);
            rollNoTextField.setText(String.valueOf(student.getRollNo()));
            nameTextField.setText(student.getName());
            standardTextField.setText(String.valueOf(student.getStandard()));
            divisionTextField.setText(String.valueOf(student.getDivision()));
        } catch (DatabaseException e){
            e.handleException();
        }
    }

    /**
     * Initializes the panel layout and appearance.
     */
    private void initializePanel(){
        setLayout(null);

        setPreferredSize(new Dimension(
                AppConstants.FORM_WIDTH, AppConstants.FORM_HEIGHT)
        );
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        setBackground(AppTheme.FORM_BACKGROUND);
    }

    /**
     * Creates and positions all labels, input fields,
     * and action buttons.
     */
    private void initializeComponents(){

        add(new AppLabel(
                formType.toString().toUpperCase() + " STUDENT DETAILS",
                LabelType.FORM_TITLE,
                AppConstants.FORM_X_OFFSET,
                AppConstants.FORM_Y_OFFSET
        ));

        addFormLabel("Roll Number:", ROLL_NUMBER_Y);
        addFormLabel("Name:", NAME_Y);
        addFormLabel("Standard:", STANDARD_Y);
        addFormLabel("Division:", DIVISION_Y);

        addInputField(rollNoTextField, ROLL_NUMBER_Y);
        addInputField(nameTextField, NAME_Y);
        addInputField(standardTextField, STANDARD_Y);
        addInputField(divisionTextField, DIVISION_Y);

        addButton(cancelButton,CANCEL_BUTTON_X);
        addButton(confirmButton, CONFIRM_BUTTON_X);
    }

    /**
     * Adds a form label at the specified vertical position.
     *
     * @param labelText the text displayed by the label
     * @param y the y-coordinate relative to the form
     */
    private void addFormLabel(String labelText, int y) {
        add(new AppLabel(
                labelText,
                LabelType.FORM,
                LABEL_X + AppConstants.FORM_X_OFFSET,
                y + AppConstants.FORM_Y_OFFSET
        ));
    }

    /**
     * Adds an input field at the specified vertical position.
     *
     * @param field the input field to add
     * @param y the y-coordinate relative to the form
     */
    private void addInputField(AppTextField field, int y) {
        field.setBounds(
                INPUT_FIELD_X + AppConstants.FORM_X_OFFSET,
                y + AppConstants.FORM_Y_OFFSET
        );
        add(field);
    }

    /**
     * Adds a button at the specified horizontal position.
     *
     * @param button the button to add
     * @param x the x-coordinate relative to the form
     */
    private void addButton(AppButton button, int x) {
        button.setBounds(
                x + AppConstants.FORM_X_OFFSET,
                BUTTON_Y + AppConstants.FORM_Y_OFFSET
        );
        add(button);
    }

    /**
     * Registers all button action listeners.
     */
    private void initializeActionListeners() {
        confirmButton.addActionListener(e -> onConfirm());
        cancelButton.addActionListener(e-> onCancel());
    }

    /**
     * Validates the entered values and performs the
     * configured add or update operation.
     *
     * <p>If the operation succeeds, a confirmation
     * message is displayed and the user is returned
     * to the student list.</p>
     */
    private void onConfirm(){
        final String rollNo = rollNoTextField.getText();
        final String name = nameTextField.getText();
        final String standard = standardTextField.getText();
        final String division = divisionTextField.getText();

        try{
            final boolean success = (formType == FormType.ADD) ?
                    studentService.addStudent(rollNo, name, standard, division) :
                    studentService.updateStudent(registrationId, rollNo, name, standard, division);
            if(success){
                final String action = formType == FormType.ADD ? "added" : "updated";
                JOptionPane.showMessageDialog(
                        this,
                        "Student " + action + " successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                onCancel();
            }
        } catch (CustomException e){
            e.handleException();
        }
    }

    /**
     * Clears the form fields and navigates back to
     * the student list.
     */
    private void onCancel(){
        clearFields();
        navigationController.navigateToViewStudents();
    }

    /**
     * Resets all input fields to their default empty state.
     */
    private void clearFields() {
        rollNoTextField.setText("");
        nameTextField.setText("");
        standardTextField.setText("");
        divisionTextField.setText("");
    }
}