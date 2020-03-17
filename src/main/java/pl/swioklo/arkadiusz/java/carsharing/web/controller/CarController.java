package pl.swioklo.arkadiusz.java.carsharing.web.controller;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.service.CarService;
import pl.swioklo.arkadiusz.java.carsharing.web.model.CarModel;

import java.util.List;
import java.util.logging.Logger;

public class CarController {
    private static final Logger LOGGER = Logger.getLogger(CarController.class.getName());
    private CarService carService;
    
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public List<CarModel> list() throws CarException {
        LOGGER.info("listing");
        return carService.list();
    }
    
    public CarModel create(CarModel carModel) throws CarException {
        LOGGER.info("Creating " + carModel);
        return carService.create(carModel);
    }
    
    public CarModel read(String vin) throws CarException {
        return carService.read(vin);
    }
    
    public CarModel update(String vin, CarModel carModel) throws CarException {
        return carService.update(vin, carModel);
    }
    
    public void delete(String vin) throws CarException {
        carService.delete(vin);
    }
}
