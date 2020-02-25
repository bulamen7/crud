package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarException extends Exception{
    public CarException(String message) {
        super(message);
    }
    
    public CarException(String message, Throwable cause) {
        super(message, cause);
    }
}
