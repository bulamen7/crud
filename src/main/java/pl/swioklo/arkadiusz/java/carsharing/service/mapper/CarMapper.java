package pl.swioklo.arkadiusz.java.carsharing.service.mapper;

import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.web.model.CarModel;

import java.util.ArrayList;
import java.util.List;

public class CarMapper {
    public CarEntity fromModelToEntity(CarModel carModel) {
        CarEntity carEntity = new CarEntity();
        
        carEntity.setVin(carModel.getVin());
        carEntity.setType(carModel.getType());
        carEntity.setModel(carModel.getModel());
        carEntity.setYearOfProduction(carModel.getYearOfProduction());
        
        return carEntity;
    }
    
    public CarModel fromEntityToModel(CarEntity carEntity) {
        CarModel carModel = new CarModel();
        
        carModel.setVin(carEntity.getVin());
        carModel.setModel(carEntity.getModel());
        carModel.setType(carEntity.getType());
        carModel.setYearOfProduction(carEntity.getYearOfProduction());
        
        return carModel;
    }
    
    public List<CarModel> fromEntitiesToModels(List<CarEntity> carEntities) {
        List<CarModel> carModels = new ArrayList<>();
        for (CarEntity carEntity : carEntities) {
            CarModel carModel = fromEntityToModel(carEntity);
            carModels.add(carModel);
        }
        return carModels;
    }
    
    public List<CarEntity> fromModelsToEntities(List<CarModel> carModels) {
        List<CarEntity> carEntities = new ArrayList<>();
        for (CarModel carModel : carModels) {
            CarEntity carEntity = fromModelToEntity(carModel);
            carEntities.add(carEntity);
        }
        return carEntities;
    }
}


//TODO zaimplementowac metode fromModelsToEntities

