package pl.swioklo.arkadiusz.java.carsharing;

import pl.swioklo.arkadiusz.java.carsharing.api.exception.CarException;
import pl.swioklo.arkadiusz.java.carsharing.dao.CarDao;
import pl.swioklo.arkadiusz.java.carsharing.service.CarService;
import pl.swioklo.arkadiusz.java.carsharing.service.mapper.CarMapper;
import pl.swioklo.arkadiusz.java.carsharing.web.controller.CarController;
import pl.swioklo.arkadiusz.java.carsharing.web.model.CarModel;

import java.util.logging.Logger;

public class CarSharingMain {
    private static final Logger LOGGER = Logger.getLogger(CarSharingMain.class.getName());
    public static final String VIN = "WAFASF3VSA";
    
    public static void main(String[] args) throws CarException {
        CarDao carDao = new CarDao();
        CarMapper carMapper = new CarMapper();
        CarService carService = new CarService(carDao, carMapper);
        CarController carController = new CarController(carService);
        
       carController.create(new CarModel("WAWEADASCCZ12131", "AUDI", "A4", 2004));
       carController.create(new CarModel("WAFASF3VSDVZVZXA", "Volksvagen", "Golf", 2002));
       carController.create(new CarModel("WAFASF3VSA", "Audi", "a8", 2010));
//        List<CarModel> carModels = carController.list();
//        LOGGER.info(carModels + " ");
        
        // Scanner scanner = new Scanner(System.in);
        carController.update("WAFASF3VSA", new CarModel("11", "sdf", "33", 100));
        System.out.println(carDao.read(VIN));
        carController.delete(VIN);
        System.out.println(carController.list());
    
    
    }
}
