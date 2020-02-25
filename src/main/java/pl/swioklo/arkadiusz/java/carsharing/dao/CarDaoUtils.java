package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CarDaoUtils {
    public String dbPath() throws CarException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/db.properties"));
        } catch (IOException e) {
            throw new CarException("reading configuration from properties fail", e);
        }
        return properties.getProperty("db.path");
    }
}
