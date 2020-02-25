package pl.swioklo.arkadiusz.java.carsharing.web.controller;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
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
    //   public CarController() {
//        carService = new CarService();
//    }
//
    
    public List<CarModel> list() {
        LOGGER.info("listing");
        return carService.list();
    }
    
    public CarModel create(CarModel carModel) {
        LOGGER.info("Creating " + carModel);
        CarModel createdCarModel = null;
        try {
            createdCarModel = carService.create(carModel);
        } catch (CarException e) {
            // throw new
            //bussinesowa obsluga wyjatkow
            //   e.printStackTrace();
        }
        return createdCarModel;
    }
    
    public CarModel read(String vin) {
       return carService.read(vin);
    }
    
    public CarModel update(String vin, CarModel carModel) {

    }
    
    public void delete(String vin) {
        carService.delete(vin);
    }
}
