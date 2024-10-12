package pl.madamusinski.book.exception;

import lombok.Getter;

@Getter
public class ReadableException extends RuntimeException {

    private final transient ErrorType errorType;
    private final String errorMessage;

    public ReadableException(ErrorType errorType) {
        this.errorType = errorType;
        this.errorMessage = null;
    }

    public ReadableException(ErrorType errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }
}
