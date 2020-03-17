package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarCreateException extends CarException {
    public CarCreateException(String message) {
        super(message);
    }
    
    public CarCreateException(String message, Throwable cause) {
        super(message, cause);
    }
}
