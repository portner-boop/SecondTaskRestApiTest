package org.example.springboot.secondtaskapitest.config;


import java.io.IOException;
import java.util.Properties;

public class URLConfig {

    public static String BASE_URL;

    static{
        Properties properties = new Properties();
        try {
            properties.load(URLConfig.class.getClassLoader().getResourceAsStream("application.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BASE_URL = properties.getProperty("base-url");
    }
}
