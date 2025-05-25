package payloads;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PayloadReader {
	
	private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = PayloadReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}


