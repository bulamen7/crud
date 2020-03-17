package pl.swioklo.arkadiusz.java.carsharing.api.exception;

public class CarManageException extends CarException {
    
    public CarManageException(String message) {
        super(message);
    }
    
    public CarManageException(String message, Throwable cause) {
        super(message, cause);
    }
}
