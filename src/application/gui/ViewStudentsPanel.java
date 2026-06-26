package application.gui;

import application.gui.utility.AppLabel;
import application.gui.utility.LabelType;
import application.model.Student;
import application.service.NavigationController;
import application.service.StudentService;
import application.theme.AppConstants;
import application.theme.AppTheme;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Displays all students currently stored in the system.
 *
 * <p>The panel retrieves student records from the application.service layer and
 * displays them in a scrollable list. Each student is represented by
 * a {@link StudentRowPanel} containing the student's information and
 * available actions.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class ViewStudentsPanel extends JPanel {

    /** Handles navigation between application screens. */
    private final NavigationController navigationController;

    private final StudentService studentService;

    /** List of students displayed by the panel. */
    private List<Student> students;

    /**
     * Creates the student view panel.
     *
     * @param controller the application's navigation controller
     */
    public ViewStudentsPanel(NavigationController controller, StudentService studentService) {
        this.navigationController = controller;
        this.studentService = studentService;

        initializePanel();
        loadStudents();
        initializeComponents();
    }

    /**
     * Initializes the panel layout and appearance.
     */
    private void initializePanel() {
        setLayout(null);
        setBackground(AppTheme.CONTENT_BACKGROUND);

        setSize(
                AppConstants.CONTENT_WIDTH,
                AppConstants.WINDOW_HEIGHT
        );

        setPreferredSize(new Dimension(
                AppConstants.CONTENT_WIDTH,
                AppConstants.WINDOW_HEIGHT
        ));
    }

    /**
     * Retrieves all students from the application.service layer.
     */
    private void loadStudents() {
        students = studentService.getAllStudents();
    }

    /**
     * Initializes all UI components.
     */
    private void initializeComponents() {

        add(new AppLabel(
                students.isEmpty()
                        ? "NO STUDENT DATA FOUND"
                        : "STUDENT INFORMATION",
                LabelType.DISPLAY_TITLE,
                0,
                80
        ));

        if (!students.isEmpty()) {
            initializeScrollPane();
        }
    }

    /**
     * Creates and adds the scroll pane containing all student rows.
     */
    private void initializeScrollPane() {
        JPanel listContainer = createStudentListContainer();

        JScrollPane scrollPane = createScrollPane(listContainer);

        add(scrollPane);
    }

    /**
     * Creates the container that holds every student row.
     *
     * @return the student list container
     */
    private JPanel createStudentListContainer() {

        JPanel listContainer = new JPanel();

        listContainer.setLayout(
                new BoxLayout(listContainer, BoxLayout.Y_AXIS)
        );

        listContainer.add(Box.createVerticalStrut(15));

        for (Student student : students) {
            listContainer.add(createStudentRowPanel(student));
        }

        return listContainer;
    }

    /**
     * Creates a row displaying the supplied student's information.
     *
     * @param student the student to display
     * @return a panel representing one student row
     */
    private JPanel createStudentRowPanel(Student student) {

        JPanel studentPanel =
                new StudentRowPanel(navigationController, studentService, student);

        studentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JPanel rowPanel = new JPanel();

        rowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        rowPanel.add(studentPanel);

        return rowPanel;
    }

    /**
     * Creates the scroll pane used to display all student rows.
     *
     * @param listContainer the panel containing the student rows
     * @return the configured scroll pane
     */
    private JScrollPane createScrollPane(JPanel listContainer) {

        JScrollPane scrollPane = new JScrollPane(listContainer);

        scrollPane.setBackground(Color.BLUE);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_NEVER
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setBounds(
                0,
                150,
                AppConstants.CONTENT_WIDTH,
                AppConstants.CONTENT_HEIGHT - 200
        );

        return scrollPane;
    }
}