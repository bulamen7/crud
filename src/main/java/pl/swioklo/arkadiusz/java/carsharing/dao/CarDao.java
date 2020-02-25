package pl.swioklo.arkadiusz.java.carsharing.dao;


import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarWriteException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDao.class.getName());
    public static String DB_FILE = "src/main/resources/db.txt";
    
    private Map<String, CarEntity> dataBase = new HashMap<>();
    
    public CarDao() throws CarException {
        CarDaoUtils carDaoUtils = new CarDaoUtils();
        try {
            DB_FILE = carDaoUtils.dbPath();
        } catch (CarException e) {
            throw new CarException("reading dbpath fail", e);
        }
    }
    
    public List<CarEntity> list() {
        List<CarEntity> carModels = new ArrayList<>(dataBase.values());
        return carModels;
    }
    
    public CarEntity create(CarEntity carEntity) throws CarWriteException {
        LOGGER.info("Creating " + carEntity);
//        CarExceptionDao carExceptionDao = new CarExceptionDao();
//        carExceptionDao.create(carEntity);
        dataBase.put(carEntity.getVin(), carEntity);
        String string = carEntity.toString() + "\n";
        LOGGER.info("db file: " + DB_FILE);
        try {
            Files.write(
                    Paths.get(DB_FILE),
                    string.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CarWriteException("blad dostepu do pliku", e);
        }
        return carEntity;
    }
    
    public CarEntity read(String vin) {
        
        for (String key : dataBase.keySet()) {
            if (key.equals(vin)) {
                return dataBase.get(key);
            }
        }
        return null;
    }
    
    
    public void update() {
    
    }
    
    public void delete(String vin) {
        Iterator<Map.Entry<String, CarEntity>> iter = dataBase.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, CarEntity> entry = iter.next();
            if (vin.equalsIgnoreCase(entry.getValue().getVin())) {
                iter.remove();
            }
        }
    }
}
