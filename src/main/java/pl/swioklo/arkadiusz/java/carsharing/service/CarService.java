package pl.swioklo.arkadiusz.java.carsharing.service;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarWriteException;
import pl.swioklo.arkadiusz.java.carsharing.dao.CarDao;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.service.mapper.CarMapper;
import pl.swioklo.arkadiusz.java.carsharing.web.model.CarModel;

import java.util.List;
import java.util.logging.Logger;

public class CarService {
    private static final Logger LOGGER = Logger.getLogger(CarService.class.getName());


    private CarDao carDao;
    private CarMapper carMapper;

    public CarService(CarDao carDao, CarMapper carMapper) {
        this.carDao = carDao;
        this.carMapper = carMapper;
    }

    public List<CarModel> list() {
        LOGGER.info("listing ");
        List<CarEntity> carEntities = carDao.list();
        return carMapper.fromEntitiesToModels(carEntities);
    }

    public CarModel create(CarModel carModel) throws CarException {
        LOGGER.info("Creating " + carModel);
        CarEntity carEntity = carMapper.fromModelToEntity(carModel);

        CarEntity createdCarEntity = null;
        try {
            createdCarEntity = carDao.create(carEntity);
        } catch (CarWriteException e) {
            throw new CarException("couldnt create a car", e);
        }

        return carMapper.fromEntityToModel(createdCarEntity);
    }

    public CarModel read(String vin) {
        for (CarEntity carEntity : carDao.list()) {
            if (carEntity.getVin().equals(vin)) {
                return carMapper.fromEntityToModel(carEntity);
            }
        }
        return null;
    }

    public CarModel update(String vin, CarModel carModel) {
//        for (CarEntity carEntity : carDao.list()) {
//            if (carEntity.getVin().equals(vin)) {
//                carEntity.setModel(carModel.getModel());
//                carEntity.setType(carModel.getType());
//                carEntity.setYearOfProduction(carModel.getYearOfProduction());
//            }
//        }

        // JJ: delegujemy wyszukiwanie konkretnego samochodu do dao
        CarEntity readCarEntity = carDao.read(vin);
        CarModel readCarModel = carMapper.fromEntityToModel(readCarEntity);

        // JJ: logika update danych samochodu
        readCarModel.setModel(carModel.getModel());
        readCarModel.setType(carModel.getType());
        readCarModel.setYearOfProduction(carModel.getYearOfProduction());

        CarEntity carEntity = carMapper.fromModelToEntity(readCarModel);
        // JJ: delegujemy update samochodu do dao
        CarEntity updatedCarEntity = carDao.update(vin, carEntity);

        return carMapper.fromEntityToModel(updatedCarEntity);
    }

    public void delete(String vin) {
        carDao.delete(vin);
    }

}

//TODO service, zaimplementowac read update delete
//TODO wywolac metody dao w service
//TODO w controllerze zaimpelentowac metody read update delete i wywolac odpowiednie metody z service