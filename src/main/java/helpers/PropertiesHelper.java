package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/test/resources/configs/configs.properties";


    // Read multiple Files
    public static Properties loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();
        // Add tất cả file Properties vào đây theo mẫu
        files.add("src/test/resources/configs/configs.properties"); //  link từ folder project đến Files

        try {
            properties = new Properties();
            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);   // đọc Files
                tempProp.load(file);
                properties.putAll(tempProp);    // đọc tất cả data của Files ghép lại với nhau
            }
            System.out.println(" Load All Config \uD83D\uDD04");
            return properties;
        } catch (IOException ioe) {
            return new Properties();
        }
    }
}
