package application.service;

import application.exception.EmptyFieldException;
import application.exception.InvalidInputTypeException;
import application.exception.InvalidInputValueException;
import application.exception.ValidationException;
import application.model.Student;

/**
 * Validates student input and creates a validated {@code Student} object.
 *
 * <p>This class performs validation on user-provided student details,
 * converts them to the appropriate data types, and constructs a
 * {@code Student} instance with a generated registration ID.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public class StudentValidator {

    /**
     * Validates the supplied student details.
     *
     * <p>If all inputs are valid, a new {@code Student} object is created
     * and returned. Otherwise, a validation-related application.exception is thrown.</p>
     *
     * @param rollNo the student's roll number
     * @param name the student's name
     * @param standard the student's class or grade
     * @param division the student's division
     *
     * @return a validated {@code Student} instance
     *
     * @throws ValidationException if any validation rule is violated
     */
    public Student validate(
            String rollNo,
            String name,
            String standard,
            String division) throws ValidationException {

        validateEmptyFields(
                rollNo,
                name,
                standard,
                division
        );

        int validatedRollNo = validateRollNo(rollNo);
        String validatedName = validateName(name);
        int validatedStandard = validateStandard(standard);
        char validatedDivision = validateDivision(division);

        String registrationId = UUIDGenerator.generateUUID(
                        validatedRollNo,
                        validatedName,
                        validatedStandard,
                        validatedDivision
                );

        return new Student(
                registrationId,
                validatedRollNo,
                validatedName,
                validatedStandard,
                validatedDivision
        );
    }

    /**
     * Validates that all required fields are non-null and non-blank.
     */
    private void validateEmptyFields(
            String rollNo,
            String name,
            String standard,
            String division
    ) throws EmptyFieldException {
        if(rollNo == null || rollNo.trim().isEmpty()){
            throw new EmptyFieldException("Roll Number");
        }
        if(name == null || name.trim().isEmpty()){
            throw new EmptyFieldException("Name");
        }
        if(standard == null || standard.trim().isEmpty()){
            throw new EmptyFieldException("Standard");
        }
        if(division == null || division.trim().isEmpty()){
            throw new EmptyFieldException("Division");
        }
    }

    /**
     * Validates and converts the student's roll number.
     */
    private int validateRollNo(String rollNo) throws ValidationException {

        int value;

        try{
            value = Integer.parseInt(rollNo.trim());
        }
        catch (NumberFormatException e){
            throw new InvalidInputTypeException(
                    "Roll Number",
                    "Roll Number must contain digits only"
            );
        }

        if(value <= 0){
            throw new InvalidInputValueException(
                    "Roll Number",
                    "Roll Number must be greater than zero."
            );
        }
        return value;
    }

    /**
     * Validates the student's name.
     */
    private String validateName(String name) throws ValidationException {

        if(!name.matches("[A-Za-z\\s]+")){
            throw new InvalidInputValueException(
                    "Name",
                    "Name can contain only letters and spaces."
            );
        }
        return name.trim();
    }

    /**
     * Validates and converts the student's standard.
     */
    private int validateStandard(String standard) throws ValidationException {
        int value;

        try{
            value = Integer.parseInt(standard.trim());
        }catch (NumberFormatException e){
            throw new InvalidInputTypeException(
                    "Standard",
                    "Standard must contain digits only."
            );
        }
        if(value <= 0 || value > 10){
            throw new InvalidInputValueException(
                    "Standard",
                    "Standard must be between 1 and 10."
            );
        }
        return value;
    }

    /**
     * Validates the student's division.
     */
    private char validateDivision(String division) throws ValidationException {
        division = division.trim().toUpperCase();
        if(division.length() > 1 || !division.matches("[A-Z]")){
            throw new InvalidInputTypeException(
                    "Division",
                    "Division must be a single letter from A to Z."
            );
        }
        return division.charAt(0);
    }
}