package application;

import application.config.ConnectionConfig;
import application.exception.DatabaseException;
import application.gui.MainFrame;

import javax.swing.SwingUtilities;
import java.sql.Connection;

/**
 * Entry point for the Student Management System application.
 *
 * <p>This class establishes the application's database connection
 * before creating and displaying the main application window.
 * If the database connection cannot be established, the application
 * terminates after displaying the corresponding error.</p>
 *
 * @author Vincent Silveira
 * @version 1.0
 */
public final class StudentManagementSystem {

    /**
     * Shared database connection used throughout the application.
     */
    public static Connection connection;

    /**
     * Starts the Student Management System.
     *
     * @param args
     *        command-line arguments (not used).
     */
    public static void main(String[] args) {

        try {
            connection = ConnectionConfig.getConnection();
        } catch (DatabaseException e) {
            e.handleException();
            return;
        }

        SwingUtilities.invokeLater(() -> {
            final MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}