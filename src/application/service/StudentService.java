package application.service;

import application.dao.StudentDAO;
import application.dao.StudentDAOImplementation;
import application.exception.DatabaseException;
import application.exception.ValidationException;
import application.model.Student;

import java.util.List;

/**
 * Provides student-related business operations.
 *
 * <p>This application.service coordinates validation and data access by using
 * {@code StudentValidator} and {@code StudentDAO}. It acts as the
 * intermediary between the user interface and the data access layer.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class StudentService {

    private final StudentDAO studentDAO;
    private final StudentValidator studentValidator;

    /**
     * Creates a new {@code StudentService} with the required
     * validator and data access objects.
     */
    public StudentService() {
        this.studentValidator = new StudentValidator();
        this.studentDAO = new StudentDAOImplementation();
    }

    /**
     * Retrieves all students from the database.
     *
     * @return a list of all students
     *
     * @throws DatabaseException if the students cannot be retrieved
     */
    public List<Student> getAllStudents() throws DatabaseException {
        return studentDAO.fetchAllStudents();
    }

    /**
     * Retrieves a student by registration ID.
     *
     * @param registrationId the student's registration ID
     *
     * @return the matching student
     *
     * @throws DatabaseException if the student cannot be retrieved
     */
    public Student getStudent(String registrationId) throws DatabaseException {
        return studentDAO.fetchStudent(registrationId);
    }

    /**
     * Validates and adds a new student.
     *
     * @param rollNo the student's roll number
     * @param name the student's name
     * @param standard the student's class or grade
     * @param division the student's division
     *
     * @return {@code true} if the student was added successfully;
     *         {@code false} otherwise
     *
     * @throws ValidationException if the supplied data is invalid
     * @throws DatabaseException if the student cannot be stored
     */
    public boolean addStudent(
            String rollNo,
            String name,
            String standard,
            String division
    ) throws ValidationException, DatabaseException {

        Student student = this.studentValidator.validate(rollNo, name, standard, division);
        return this.studentDAO.addStudent(student);
    }

    /**
     * Validates and updates an existing student.
     *
     * @param registrationId the student's registration ID
     * @param rollNo the student's roll number
     * @param name the student's name
     * @param standard the student's class or grade
     * @param division the student's division
     *
     * @return {@code true} if the student was updated successfully;
     *         {@code false} otherwise
     *
     * @throws ValidationException if the supplied data is invalid
     * @throws DatabaseException if the update fails
     */
    public boolean updateStudent(
            String registrationId,
            String rollNo,
            String name,
            String standard,
            String division
    ) throws ValidationException, DatabaseException {

        Student student = this.studentValidator.validate(rollNo, name, standard, division);
        student.setRegistrationId(registrationId);

        return this.studentDAO.updateStudent(student);
    }

    /**
     * Deletes a student by registration ID.
     *
     * @param registrationId the student's registration ID
     *
     * @return {@code true} if the student was deleted successfully;
     *         {@code false} otherwise
     *
     * @throws DatabaseException if the deletion fails
     */
    public boolean deleteStudent(String registrationId)
            throws DatabaseException {

        return studentDAO.deleteStudent(registrationId);
    }
}
