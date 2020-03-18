package pl.swioklo.arkadiusz.java.carsharing.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.swioklo.arkadiusz.java.carsharing.dao.entity.CarEntity;
import pl.swioklo.arkadiusz.java.carsharing.web.model.CarModel;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarMapperTest {
    CarMapper carMapper;
    CarModel carModel;
    CarEntity carEntity;
    
    
    @BeforeEach
    void setUp() {
        CarMapper carMapper = new CarMapper();
        CarModel carModel = new CarModel();
        CarEntity carEntity = new CarEntity();
    }
    
    @Test
    void givenCarMapper_whenFromModelToEntity_thenCarEntityNotNull() {
        //given
        
        
        //when
        carEntity = carMapper.fromModelToEntity(carModel);
        
        //then
        assertNotNull(carEntity, "carEntity is null");
    }
    
    
    @Test
    void givenCarMapper_whenFromEntityToModel_thenCarModelNotNull() {
        //given
        
        //when
        CarModel carModel = carMapper.fromEntityToModel(carEntity);
        
        //then
        assertNotNull(carModel, "carModel is null");
    }
    
    @Test
    void givenCarMapper_whenFromEntitiesToModels_thenCarModelsNotNull() {
        //given
   
        List<CarEntity> carEntity = new ArrayList<>();
        
        //when
        List<CarModel> carModels = carMapper.fromEntitiesToModels(carEntity);
        
        //then
        assertNotNull(carModels, "carModelList is null");
    }
    
    @Test
    void givenCarMapper_whenFromModelsToEntities_thenCarEntitiesNotNull() {
        //given
        List<CarModel> carModels = new ArrayList<>();
        
        //when
        List<CarEntity> carEntities = carMapper.fromModelsToEntities(carModels);
        
        //then
        assertNotNull(carEntities, "carEntities is null");
    }
}
//TODO:: dokonczyc testy