package pl.madamusinski.book.api;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.madamusinski.book.exception.ApiErrorResponse;
import pl.madamusinski.book.exception.ErrorType;
import pl.madamusinski.book.exception.ReadableException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleReadableException(ReadableException e) {
        return ResponseEntity.status(HttpStatus.valueOf(e.getErrorType().getStatusCode()))
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(ApiErrorResponse.from(e.getErrorType(), e.getErrorMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var result = e.getBindingResult();

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors()
                .forEach(fieldError -> errorList.add(fieldError.getField() + " [" + fieldError.getRejectedValue() + "] " + fieldError.getDefaultMessage()));

        result.getGlobalErrors()
                .forEach(globalError -> errorList.add(globalError.getObjectName() + " : " + globalError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(ApiErrorResponse.from(ErrorType.BAD_REQUEST, errorList.stream().sorted().reduce("", (str1, str2) -> str1 + " " + str2).trim()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleUnknownException(Exception e) {
        log.error("Unknown Exception", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .body(ApiErrorResponse.from(ErrorType.SERVER_ERROR, e.getMessage()));
    }
}
