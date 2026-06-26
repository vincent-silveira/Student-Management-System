package application.dao;

/**
 * Stores SQL queries used by Student DAO operations.
 *
 * <p>This class centralizes all Student related SQL statements
 * to keep database queries separated from DAO implementation logic.</p>
 *
 * <p>This class follows the utility class pattern:
 * <ul>
 *     <li>Contains only static constants</li>
 *     <li>Cannot be instantiated</li>
 * </ul>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class StudentQueries {

    /**
     * Query used to retrieve all student records.
     */
    public static final String FETCH_ALL =
            "SELECT * FROM STUDENT";

    /**
     * Query used to retrieve a student by registration id.
     *
     * <p>Requires one parameter:
     * registration_id</p>
     */
    public static final String FIND_BY_ID =
            "SELECT * FROM STUDENT WHERE registration_id = ?";

    /**
     * Query used to insert a new student record.
     *
     * <p>Parameters required:
     * <ul>
     *     <li>registration_id</li>
     *     <li>roll_no</li>
     *     <li>name</li>
     *     <li>class</li>
     *     <li>division</li>
     * </ul>
     */
    public static final String INSERT =
            "INSERT INTO STUDENT(registration_id, roll_no, name, class, division) VALUES (?, ?, ?, ?, ?)";

    /**
     * Query used to update an existing student record.
     *
     * <p>Updates:
     * <ul>
     *     <li>roll number</li>
     *     <li>name</li>
     *     <li>class</li>
     *     <li>division</li>
     * </ul>
     *
     * <p>Uses registration_id to identify the student.</p>
     */
    public static final String UPDATE =
            "UPDATE STUDENT SET roll_no = ?, name = ?, class = ?, division = ? WHERE registration_id = ?";

    /**
     * Query used to delete a student record.
     *
     * <p>Requires:
     * registration_id of the student to remove.</p>
     */
    public static final String DELETE =
            "DELETE FROM STUDENT WHERE registration_id = ?";


    /**
     * Prevents creation of StudentQueries objects.
     *
     * <p>This class only contains static query constants
     * and should not be instantiated.</p>
     *
     * @throws IllegalStateException
     *         if an attempt is made to create an instance.
     */
    private StudentQueries(){
        throw new IllegalStateException("StudentQueries class cannot be instantiated");
    }
}
