package application.service;

import java.util.UUID;

/**
 * Utility class responsible for generating unique student identifiers.
 *
 * <p>The generated identifier is based on the current timestamp and
 * student details such as roll number, name, standard, and division.</p>
 *
 * <p>This class cannot be instantiated because it only provides
 * static utility methods.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class UUIDGenerator {

    /**
     * Private constructor to prevent object creation.
     */
    private UUIDGenerator() {
        throw new IllegalStateException("UUIDGenerator class cannot be instantiated");
    }

    /**
     * Generates a unique UUID string for a student.
     *
     * <p>The UUID is created from a combination of:
     * <ul>
     *     <li>Current system timestamp</li>
     *     <li>Student roll number</li>
     *     <li>Student name</li>
     *     <li>Student standard</li>
     *     <li>Student division</li>
     * </ul>
     *
     * @param rollNo student roll number
     * @param name student name
     * @param standard student standard/class level
     * @param division student division
     *
     * @return generated UUID as a String
     */
    public static String generateUUID(
            int rollNo,
            String name,
            int standard,
            char division
    ){
        long timeStamp = System.currentTimeMillis();

        String studentData = 
                timeStamp + ":" + 
                rollNo + ":" +
                name + ":" +
                standard + ":" +
                division;        
        
        return UUID
                .nameUUIDFromBytes(studentData.getBytes())
                .toString();
    }
}