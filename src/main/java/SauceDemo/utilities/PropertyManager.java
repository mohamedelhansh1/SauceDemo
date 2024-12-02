package SauceDemo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyManager {
    // Declare static variables for property values
    private static String url, validUsername, validPassword, invalidUsername, invalidPassword;

    // Singleton instance of the PropertyManager
    private static PropertyManager instance;

    /**
     * Returns the single instance of PropertyManager, initializing it if necessary.
     * This ensures only one instance of PropertyManager exists throughout the application.
     */
    public static PropertyManager getInstance() {
        if (instance == null) { // Check if instance is already initialized
            instance = new PropertyManager(); // Create a new instance
            instance.loadData(); // Load data from properties file
        }
        return instance; // Return the single instance
    }

    /**
     * Loads data from the configuration properties file into static variables.
     */
    private void loadData() {
        Properties properties = new Properties(); // Create a Properties object

        try {
            // Load the properties file
            FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/configuration.properties");
            properties.load(fi); // Load properties from the file

            // Assign values to static variables
            url = properties.getProperty("url");
            validUsername = properties.getProperty("validUsername");
            validPassword = properties.getProperty("validPassword");
            invalidUsername = properties.getProperty("invalidUsername");
            invalidPassword = properties.getProperty("invalidPassword");
        } catch (Exception exception) {
            exception.printStackTrace(); // Print stack trace if an exception occurs
        }
    }



    // Getter methods for retrieving property values
    public String getUrl() {
        return url; // Returns the application URL
    }

    public String getValidUsername() {
        return validUsername; // Returns the valid username
    }

    public String getValidPassword() {
        return validPassword; // Returns the valid password
    }

    public String getInvalidUsername() {
        return invalidUsername; // Returns the invalid username
    }

    public String getInvalidPassword() {
        return invalidPassword; // Returns the invalid password
    }

}
