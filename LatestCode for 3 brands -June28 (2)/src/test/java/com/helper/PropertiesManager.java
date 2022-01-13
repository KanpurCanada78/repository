package com.helper;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
	
	static Properties properties;

	public static Properties getProperties() {
	    if(null == properties) {
            properties = new Properties();
            FileInputStream iStr = null;
            try {
                iStr = new FileInputStream("config.properties");
                properties.load(iStr);
                iStr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	    return properties;
    }

    /**
     * This function initializes the test settings from the default properties file with the name "testsettings.properties" present in the current working directory
     * @throws IOException
     */
    public static void initializeProperties() throws IOException {

    }

    /**
     * This function is used to get the value of a particular test setting
     * @param propertyName - The name of the property/key/setting
     * @return
     */
    public String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName,"Not Found");
    }
}