package application.dao;


import application.StudentManagementSystem;
import application.exception.DatabaseException;
import application.exception.ValidationException;
import application.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides database access operations for the Student entity.
 *
 * <p>This class implements {@link StudentDAO} and acts as the data access
 * layer between the application and the database.</p>
 *
 * <p>The class provides CRUD operations:</p>
 * <ul>
 *     <li>Create - Add a new student record</li>
 *     <li>Read - Fetch one or all students</li>
 *     <li>Update - Modify existing student information</li>
 *     <li>Delete - Remove student records</li>
 * </ul>
 *
 * <p>Database operations are performed using JDBC PreparedStatements
 * to ensure safe query execution.</p>
 *
 * <p>Validation is performed before executing database operations.
 * Database failures are reported through {@link DatabaseException} and
 * invalid input is reported through {@link ValidationException}.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class StudentDAOImplementation implements StudentDAO {
    private final Connection connection;

    /**
     * Creates a StudentDAO implementation using the application's
     * shared database connection.
     */
    public StudentDAOImplementation(){
        connection = StudentManagementSystem.connection;
    }

    /**
     * Converts the current row of a ResultSet into a Student object.
     *
     * <p>This method extracts values from the database result and maps
     * them into the Student application.model.</p>
     *
     * @param rs ResultSet containing student record data
     *
     * @return Student object created from database values
     *
     * @throws SQLException
     *         if a database column cannot be accessed
     */
    private Student mapStudent(ResultSet rs) throws SQLException{
        return new Student(
                rs.getString("registration_id"),
                rs.getInt("roll_no"),
                rs.getString("name"),
                rs.getInt("class"),
                rs.getString("division").charAt(0)
        );
    }


    /**
     * Validates the provided registration id.
     *
     * <p>This method ensures that a registration id is present before
     * performing database operations that require a student identifier.</p>
     *
     * @param registrationId
     *        student registration identifier to validate.
     *
     * @throws ValidationException
     *         if the registration id is null or missing.
     */
    private void validateRegistrationId(String registrationId) throws ValidationException {
        if (registrationId == null) {
            throw new ValidationException(
                    "Incomplete Request",
                    "Registration Id is missing"
            );
        }
    }

    /**
     * Validates the provided Student object.
     *
     * <p>This method ensures that a valid Student object is supplied
     * before executing database operations such as insert or update.</p>
     *
     * @param student
     *        student object to validate.
     *
     * @throws ValidationException
     *         if the student object is null.
     */
    private void validateStudent(Student student) throws ValidationException {
        if(student == null){
            throw new ValidationException(
                    "Incomplete Request",
                    "Student values are missing"
            );
        }
    }


    /**
     * Retrieves all students stored in the database.
     *
     * <p>The method executes the fetch-all query, iterates through the
     * ResultSet and converts every row into a Student object.</p>
     *
     * @return List<Student>
     *         containing all available student records.
     *
     * @throws DatabaseException
     *         if the database query fails.
     */
    @Override
    public List<Student> fetchAllStudents()
            throws DatabaseException {

        List<Student> arrayList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(StudentQueries.FETCH_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                arrayList.add(mapStudent(rs));
            }
        } catch (SQLException e) {
            throw new DatabaseException(
                    "Student Retrieval Failed",
                    "Unable to retrieve students from the database."
            );
        }
        return arrayList;
    }

    /**
     * Retrieves a student using the registration id.
     *
     * <p>The registration id is validated before executing the query.
     * If no matching student exists, a DatabaseException is thrown.</p>
     *
     * @param registrationId
     *        unique identifier of the student.
     *
     * @return Student
     *         matching the provided registration id.
     *
     * @throws ValidationException
     *         if registration id is missing.
     *
     * @throws DatabaseException
     *         if database access fails.
     */
    @Override
    public Student fetchStudent(String registrationId)
            throws DatabaseException, ValidationException {

        validateRegistrationId(registrationId);

        try (PreparedStatement ps = connection.prepareStatement(StudentQueries.FIND_BY_ID)) {
            ps.setString(1, registrationId);

            try (ResultSet rs = ps.executeQuery()) {

                if (!rs.next()) {
                    throw new DatabaseException(
                            "Student Not Found",
                            "No student found with registration id: " + registrationId
                    );
                }
                return mapStudent(rs);
            }
        } catch (SQLException e) {
            throw new DatabaseException(
                    "Student Lookup Failed",
                    "Unable to retrieve student with registration id: " + registrationId
            );
        }
    }

    /**
     * Adds a new student record into the database.
     *
     * <p>The student object is validated before insertion.
     * Data is inserted using a PreparedStatement.</p>
     *
     * @param student
     *        student information to store.
     *
     * @return boolean
     *         true if insertion succeeds, otherwise false.
     *
     * @throws ValidationException
     *         if student data is missing.
     *
     * @throws DatabaseException
     *         if insertion fails.
     */
    @Override
    public boolean addStudent(Student student)
            throws DatabaseException, ValidationException {

        validateStudent(student);

        try (PreparedStatement ps = connection.prepareStatement(StudentQueries.INSERT)) {
            ps.setString(1, student.getRegistrationId());
            ps.setInt(2, student.getRollNo());
            ps.setString(3, student.getName());
            ps.setInt(4, student.getStandard());
            ps.setString(5, String.valueOf(student.getDivision()));

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseException(
                    "Student Creation Failed",
                    "Unable to save student record."
            );
        }
    }

    /**
     * Updates an existing student record.
     *
     * <p>The student is identified using registration id.
     * The following fields are updated:</p>
     *
     * <ul>
     *     <li>Roll number</li>
     *     <li>Name</li>
     *     <li>Class</li>
     *     <li>Division</li>
     * </ul>
     *
     * @param student
     *        updated student information.
     *
     * @return boolean
     *         true if update succeeds.
     *
     * @throws ValidationException
     *         if student data is missing.
     *
     * @throws DatabaseException
     *         if update operation fails.
     */
    @Override
    public boolean updateStudent(Student student)
            throws DatabaseException, ValidationException {

        validateStudent(student);

        try (PreparedStatement ps = connection.prepareStatement(StudentQueries.UPDATE)) {

            ps.setInt(1, student.getRollNo());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getStandard());
            ps.setString(4, String.valueOf(student.getDivision()));
            ps.setString(5, student.getRegistrationId());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new DatabaseException(
                    "Student Update Failed",
                    "Unable to update student record."
            );
        }

    }

    /**
     * Deletes a student from the database.
     *
     * <p>The record is removed using the provided registration id.</p>
     *
     * @param registrationId
     *        identifier of the student to delete.
     *
     * @return boolean
     *         true if deletion succeeds.
     *
     * @throws ValidationException
     *         if registration id is missing.
     *
     * @throws DatabaseException
     *         if delete operation fails.
     */
    @Override
    public boolean deleteStudent(String registrationId) throws DatabaseException, ValidationException {
        validateRegistrationId(registrationId);

        try (PreparedStatement ps = connection.prepareStatement(StudentQueries.DELETE)) {
            ps.setString(1, registrationId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new DatabaseException(
                    "Student Deletion Failed",
                    "Unable to delete student record."
            );
        }
    }
}