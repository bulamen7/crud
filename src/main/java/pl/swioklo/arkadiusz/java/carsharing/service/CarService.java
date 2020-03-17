package pl.swioklo.arkadiusz.java.carsharing.service;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarCreateException;
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

    public List<CarModel> list() throws CarException {
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
        } catch (CarCreateException e) {
            throw new CarException("couldnt create a car", e);
        }

        return carMapper.fromEntityToModel(createdCarEntity);
    }

    public CarModel read(String vin) throws CarException {
        for (CarEntity carEntity : carDao.list()) {
            if (carEntity.getVin().equals(vin)) {
                return carMapper.fromEntityToModel(carEntity);
            }
        }
        return null;
    }

    public CarModel update(String vin, CarModel carModel) throws CarException {
    

        CarEntity readCarEntity = carDao.read(vin);
        CarModel readCarModel = carMapper.fromEntityToModel(readCarEntity);

        readCarModel.setModel(carModel.getModel());
        readCarModel.setType(carModel.getType());
        readCarModel.setYearOfProduction(carModel.getYearOfProduction());

        CarEntity carEntity = carMapper.fromModelToEntity(readCarModel);

        CarEntity updatedCarEntity = carDao.update(vin, carEntity);

        return carMapper.fromEntityToModel(updatedCarEntity);

    }

    public void delete(String vin) throws CarException {
        carDao.delete(vin);
    }

}
