package pl.swioklo.arkadiusz.java.carsharing.dao.entity;

public class CarEntity {
    private String vin;
    private String model;
    private String type;
    private int yearOfProduction;
    
    public CarEntity() {
    }
    
    public CarEntity(String vin, String model, String type, int yearOfProduction) {
        this.vin = vin;
        this.model = model;
        this.type = type;
        this.yearOfProduction = yearOfProduction;
    }
    
    public String getVin() {
        return vin;
    }
    
    public void setVin(String vin) {
        this.vin = vin;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public int getYearOfProduction() {
        return yearOfProduction;
    }
    
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
    
    @Override
    public String toString() {
        return "CarEntity{" +
                "vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
    
}
