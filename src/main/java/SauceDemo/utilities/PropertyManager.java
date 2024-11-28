package SauceDemo.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyManager {
    private static String url, validUsername, validPassword, invalidUsername, invalidPassword;
    private static PropertyManager instance;

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
            instance.loadData();
        }

        return instance;
    }
    private void loadData(){
        Properties properties = new Properties();

        try{
            FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "src/main/java/resources/configuration.properties");
            properties.load(fi);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        url = properties.getProperty("url");
        validPassword = properties.getProperty("validPassword");
        validUsername = properties.getProperty("validUsername");
        invalidUsername = properties.getProperty("invalidUsername");
        invalidPassword = properties.getProperty("invalidPassword");
    }
    public static void changeProperty(String key, String value){
        Properties properties = new Properties();
        try{
            FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "src/main/java/resources/configuration.properties");
            properties.load(fi);
            properties.setProperty(key, value);
            properties.store(new FileOutputStream(System.getProperty("user.dir") + "src/main/java/resources/configuration.properties"), null);
        } catch (Exception exception){
            exception.printStackTrace();
        }
        instance = null;
    }
    public String getUrl(){
        return url;
    }

    public String getValidUsername() {
        return validUsername;
    }

    public String getValidPassword() {
        return validPassword;
    }

    public String getInvalidUsername() {
        return invalidUsername;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
