package pl.swioklo.arkadiusz.java.carsharing.service;

import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

public class CarCsvUtils {
    
    public static final String CSV_SEPARATOR = ",";
    
    public static String carToCsv(CarEntity carEntity) {
        return carEntity.getVin() + CSV_SEPARATOR
                + carEntity.getModel() + CSV_SEPARATOR
                + carEntity.getType() + CSV_SEPARATOR
                + carEntity.getYearOfProduction();
    }
    
    public static CarEntity csvToCar(String csvLine) {
        String[] split = csvLine.split(CSV_SEPARATOR);
        
        CarEntity carEntity = new CarEntity();
        carEntity.setVin(split[0]);
        carEntity.setModel(split[1]);
        carEntity.setType(split[2]);
        carEntity.setYearOfProduction(Integer.parseInt(split[3]));
        
        return carEntity;
    }
}
