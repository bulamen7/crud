package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarDataBaseException extends CarException {
    
    public CarDataBaseException(String message) {
        super(message);
    }
    
    public CarDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
