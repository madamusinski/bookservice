package pl.madamusinski.book.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    BAD_REQUEST(404, "badRequest", "Bad request"),
    BOOK_NOT_FOUND_BY_ISBN(404, "badRequest", "Book with such isbn not found"),
    SERVER_ERROR(500, "serverError", "Server error");


    private final int statusCode;
    private final String key;
    private final String description;

    ErrorType(int statusCode, String key, String description) {
        this.statusCode = statusCode;
        this.key = key;
        this.description = description;
    }
}
