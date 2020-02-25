package pl.swioklo.arkadiusz.java.carsharing.dao;

import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;

public class CarFileDaoMain {
    public static void main(String[] args) {
        CarFileDao carFileDao = new CarFileDao();
        carFileDao.create(new CarEntity());
    }
}
