package pl.swioklo.arkadiusz.java.carsharing.dao;


import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarWriteException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.service.CarCsvUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDao.class.getName());
    public static final String NEW_LINE_SEPARATOR = "\n";
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
        List<CarEntity> carEntities = new ArrayList<>();
        try {
            List<String> strings = Files.readAllLines(Paths.get(DB_FILE));
            for (String string : strings) {
                CarEntity carEntity = CarCsvUtils.csvToCar(string);
                carEntities.add(carEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //List<CarEntity> carModels = new ArrayList<>(dataBase.values());
        return carEntities;
    }
    
    public CarEntity create(CarEntity carEntity) throws CarWriteException {
        LOGGER.info("Creating " + carEntity);
//        CarExceptionDao carExceptionDao = new CarExceptionDao();
//        carExceptionDao.create(carEntity);
        //  dataBase.put(carEntity.getVin(), carEntity);
        String string = CarCsvUtils.carToCsv(carEntity) + "\n";
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
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public CarEntity update(String vin, CarEntity updateCarEntity) {
        File file = Paths.get(DB_FILE).toFile();
        
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            StringBuilder oldContent = new StringBuilder();
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                CarEntity carEntity = CarCsvUtils.csvToCar(readLine);
                if (vin.equalsIgnoreCase(carEntity.getVin())) {
                    String csvLine = CarCsvUtils.carToCsv(updateCarEntity);
                    oldContent.append(csvLine).append(NEW_LINE_SEPARATOR);
                } else {
                    oldContent.append(readLine).append("\n");
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(oldContent.toString());
            
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return updateCarEntity;
    }
    
    
    public void delete(String vin) {
        File file = Paths.get(DB_FILE).toFile();
        
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            StringBuilder oldContent = new StringBuilder();
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                CarEntity carEntity = CarCsvUtils.csvToCar(readLine);
                if (!vin.equalsIgnoreCase(carEntity.getVin())) {
                    oldContent.append(readLine).append("\n");
                    //String csvLine = CarCsvUtils.carToCsv(updateCarEntity);
                    // oldContent.append(csvLine).append(NEW_LINE_SEPARATOR);
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(oldContent.toString());
            
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //        CarEntity carEntity = dataBase.get(vin);
//        dataBase.remove(carEntity);
    }
}
