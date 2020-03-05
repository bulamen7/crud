package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.service.CarCsvUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class CommonCarDao {
    private static final Logger LOGGER = Logger.getLogger(CarDao.class.getName());
    public static final String NEW_LINE_SEPARATOR = "\n";
    public static String DB_FILE = "src/main/resources/db.txt";
    
    public CarEntity updateOrDelete(String vin, CarEntity updateCarEntity, boolean update) {
        File file = Paths.get(DB_FILE).toFile();
        
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            
            StringBuilder oldContent = new StringBuilder();
            String readLine;
            while ((readLine = bufferedReader.readLine()) != null) {
                CarEntity carEntity = CarCsvUtils.csvToCar(readLine);
                if (vin.equalsIgnoreCase(carEntity.getVin())) {
                    if (update) {
                        String csvLine = CarCsvUtils.carToCsv(updateCarEntity);
                        oldContent.append(csvLine).append(NEW_LINE_SEPARATOR);
                    }
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
    
}
