package pl.madamusinski.book.exception;

import jakarta.validation.constraints.NotBlank;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public record ApiErrorResponse(int status, @NotBlank String error, @NotBlank String description, String message) {

    public static ApiErrorResponse from(ErrorType errorType) {
        return new ApiErrorResponse(errorType.getStatusCode(), errorType.getKey(), errorType.getDescription(), EMPTY);
    }

    public static ApiErrorResponse from(ErrorType errorType, String message) {
        return new ApiErrorResponse(errorType.getStatusCode(), errorType.getKey(), errorType.getDescription(), message);
    }
}
