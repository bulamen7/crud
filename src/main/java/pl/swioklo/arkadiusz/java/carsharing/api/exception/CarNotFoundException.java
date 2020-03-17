package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarNotFoundException extends CarException {
    
    public CarNotFoundException(String message) {
        super(message);
    }
    
    public CarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
