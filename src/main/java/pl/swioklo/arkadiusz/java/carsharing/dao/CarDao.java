package pl.swioklo.arkadiusz.java.carsharing.dao;


import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarDataBaseException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarCreateException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarNotFoundException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.service.CarCsvUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static pl.swioklo.arkadiusz.java.carsharing.dao.CommonCarDao.DB_FILE;

public class CarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDao.class.getName());
    
    private Map<String, CarEntity> dataBase = new HashMap<>();
    
    public CarDao() throws CarException {
        CarDaoUtils carDaoUtils = new CarDaoUtils();
        try {
            DB_FILE = carDaoUtils.dbPath();
        } catch (CarException e) {
            throw new CarDataBaseException("reading dbpath fail", e);
        }
    }
    
    public List<CarEntity> list() throws CarException {
        List<CarEntity> carEntities = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(DB_FILE));
            for (String string : strings) {
                CarEntity carEntity = CarCsvUtils.csvToCar(string);
                carEntities.add(carEntity);
            }
        } catch (IOException e) {
            throw new CarException("Blad podczas odczytu listy samochodow", e);
        }
        return carEntities;
    }
    
    public CarEntity create(CarEntity carEntity) throws CarException {
        LOGGER.info("Creating " + carEntity);
        
        String string = CarCsvUtils.carToCsv(carEntity) + "\n";
        LOGGER.info("db file: " + DB_FILE);
        try {
            Files.write(
                    Paths.get(DB_FILE),
                    string.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CarCreateException("blad dostepu do pliku", e);
        }
        return carEntity;
    }
    
    public CarEntity read(String vin) throws CarException {
        try (FileReader fileReader = new FileReader(Paths.get(DB_FILE).toFile());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                CarEntity carEntity = CarCsvUtils.csvToCar(readLine);
                if (vin.equalsIgnoreCase(carEntity.getVin())) {
                    LOGGER.info("ReadLine " + readLine);
                    return carEntity;
                }
            }
        } catch (FileNotFoundException e) {
            throw new CarDataBaseException("blad podczas odczytu pliku", e);
        } catch (IOException e) {
            throw new CarNotFoundException("Blad podczas odczytu informacji o samchodzie", e);
        }
        return null;
    }
    
    public CarEntity update(String vin, CarEntity updateCarEntity) throws CarException {
        
        CommonCarDao commonCarDao = new CommonCarDao();
        return commonCarDao.updateOrDelete(vin, updateCarEntity, true);
    }
    
    
    public void delete(String vin) throws CarException {
        
        CommonCarDao commonCarDao = new CommonCarDao();
        commonCarDao.updateOrDelete(vin, null, false);
    }
}
