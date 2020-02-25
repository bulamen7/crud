package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarWriteException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

public class CarExceptionDao {
    public CarEntity create(CarEntity carEntity) throws CarWriteException {
        throw new CarWriteException("blad zapisu");
    }
}
