package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class CarFileDao {
    public CarEntity create(CarEntity carEntity) {
        FileWriter fileWriter = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            String string = carEntity.toString() + "\n";
            //highlevel API java 1.7

//            Files.write(
//                    Paths.get("src/main/resources/db.txt"),
//                    string.getBytes(),
            //         StandardOpenOption.APPEND);
            //midlevel API -wrappers java 1.1
//            fileWriter = new FileWriter("src/main/resources/db.txt");
//            fileWriter.write(string);
            
            //lowlevel API java 1.0
            
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/db.txt");
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                //w fileWriterze obowiazkowe jest zamkniecie strumienia za pomoca .close
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
    
    public CarEntity createWithResource(CarEntity carEntity) {
        try (FileWriter fileWriter = new FileWriter("src/main/resources/db.txt")) {
            String string = carEntity.toString() + "\n";
            fileWriter.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}













