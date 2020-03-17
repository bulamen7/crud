package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarCreateException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

public class CarExceptionDao {
    public CarEntity create(CarEntity carEntity) throws CarCreateException {
        throw new CarCreateException("blad zapisu");
    }
}
