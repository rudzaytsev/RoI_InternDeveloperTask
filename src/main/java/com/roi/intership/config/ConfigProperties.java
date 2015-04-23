package com.roi.intership.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rudolph on 23.04.15.
 */
public class ConfigProperties {

    private static final String propFileName = "config.properties";

    private Properties properties = new Properties();

    public void load() throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);

        } else {
            throw new FileNotFoundException("Property file '" + propFileName + "' not found");
        }

    }

    public String getProperty(String propertyName){
        return properties.getProperty(propertyName);
    }


}
