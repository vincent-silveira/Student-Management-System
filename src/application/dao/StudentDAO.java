package application.dao;

import application.model.Student;

import java.util.List;

/**
 * Defines database operations for the Student entity.
 *
 * <p>This interface represents the contract for student data access.
 * Implementations are responsible for communicating with the database
 * and performing CRUD operations.</p>
 *
 * <p>Operations provided:</p>
 * <ul>
 *     <li>Retrieve all students</li>
 *     <li>Retrieve a student by registration id</li>
 *     <li>Add a new student</li>
 *     <li>Update existing student information</li>
 *     <li>Delete a student</li>
 * </ul>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public interface StudentDAO {

    /**
     * Retrieves all student records.
     *
     * <p>This method returns all students currently stored
     * in the system.</p>
     *
     * @return list containing all student records.
     */
    List<Student> fetchAllStudents();

    /**
     * Retrieves a student by registration id.
     *
     * <p>This method searches for a student using the provided
     * unique registration identifier.</p>
     *
     * @param registrationId
     *        registration id of the student to retrieve.
     *
     * @return student object if found.
     */
    Student fetchStudent(String registrationId);

    /**
     * Adds a new student record.
     *
     * <p>This method stores the provided student information
     * in the system.</p>
     *
     * @param student
     *        student object containing the information to save.
     *
     * @return true if the student was added successfully,
     *         false otherwise.
     */
    boolean addStudent(Student student);

    /**
     * Updates an existing student record.
     *
     * <p>This method modifies the stored student information
     * using the provided student object.</p>
     *
     * @param student
     *        student object containing updated information.
     *
     * @return true if the update was successful,
     *         false otherwise.
     */
    boolean updateStudent(Student student);

    /**
     * Deletes a student record.
     *
     * <p>This method removes a student from the system using
     * the provided registration id.</p>
     *
     * @param registrationId
     *        registration id of the student to delete.
     *
     * @return true if deletion was successful,
     *         false otherwise.
     */
    boolean deleteStudent(String registrationId);
}
