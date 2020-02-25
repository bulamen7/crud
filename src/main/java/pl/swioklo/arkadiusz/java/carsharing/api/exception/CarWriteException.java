package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarWriteException extends CarException {
    public CarWriteException(String message) {
        super(message);
    }
    
    public CarWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
