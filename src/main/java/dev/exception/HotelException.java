package dev.exception;

public class HotelException extends RuntimeException {

    public HotelException() {
    }

    public HotelException(String message) {
        super(message);
    }

    public HotelException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelException(Throwable cause) {
        super(cause);
    }
}
