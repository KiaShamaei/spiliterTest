package snapp.pay.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



public class CustomExceptionHandler extends RuntimeException {

    public CustomExceptionHandler(String message) {
        super(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> assertionException(CustomerNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 409
    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<?> assertionException(GroupNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)  // 409
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<?> assertionException(CustomerAlreadyExistsException e) {
        return error(e, HttpStatus.FORBIDDEN, e.getLocalizedMessage());
    }

    private ResponseEntity<?> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = "Error";
        return new ResponseEntity<>(message, httpStatus);
    }
}
