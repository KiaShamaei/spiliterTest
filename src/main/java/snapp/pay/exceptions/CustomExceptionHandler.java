package snapp.pay.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Base Handler for Exception
 * @Author Kiarash Shamaei 2023-06-25
 */
@Slf4j
@ControllerAdvice
@NoArgsConstructor
public class CustomExceptionHandler {


    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<?> assertionException(CustomerNotFoundException e , WebRequest request) {
        log.error("error not found for request {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = GroupNotFoundException.class)
    public ResponseEntity<?> assertionException(GroupNotFoundException e) {
        log.error("error Group Not Found Exception for request {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<?> assertionException(CustomerAlreadyExistsException e) {
        log.error("error Customer Already Exists Exception for request {}" , e.getMessage());
        return error(e, HttpStatus.FORBIDDEN, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        log.error("error Customer Already Exists Exception for request {}" , e.getMessage());
        return error(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    private ResponseEntity<?> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = "Error";
        return new ResponseEntity<>(message, httpStatus);
    }

}
