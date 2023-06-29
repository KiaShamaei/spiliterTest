package snapp.pay.exceptions;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import snapp.pay.dto.ErrorResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Base Handler for Exception
 * @Author Kiarash Shamaei 2023-06-25
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<?> assertionException(CustomerNotFoundException e , WebRequest request) {
        log.error("error not found for request {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = GroupNotFoundException.class)
    public ResponseEntity<?> assertionException(GroupNotFoundException e) {
        log.error("error Group Not Found Exception with this {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
    @ExceptionHandler(value = GroupNameAlreadyExistException.class)
    public ResponseEntity<?> assertionException(GroupNameAlreadyExistException e) {
        log.error("error Group Not Found Exception with this {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
    @ExceptionHandler(value = BillNotFoundException.class)
    public ResponseEntity<?> assertionException(BillNotFoundException e) {
        log.error("error BillNotFoundException Not Found Exception with this {}" , e.getMessage());
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
    @ExceptionHandler
    public ResponseEntity<?> assertionException(IllegalArgumentException e) {
        log.error("error Group Not Found Exception with this {}" , e.getMessage());
        return error(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = CustomerAlreadyExistsException.class)
    public ResponseEntity<?> assertionException(CustomerAlreadyExistsException e) {
        log.error("error Customer Already Exists Exception with this {}" , e.getMessage());
        return error(e, HttpStatus.FORBIDDEN, e.getLocalizedMessage());
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<?> handleMethodArgument(MethodArgumentNotValidException e) {
        log.error("error Customer Already Exists Exception with this {}" , e.getMessage());
        List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        var error = ErrorResponse.builder()
                .errorCode(HttpStatus.BAD_REQUEST.value())
                .errors(getErrorsMap(errors))
                .message(errors.get(0))
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = BadCredentialsException.class)
    public final ResponseEntity<?> userPasswordException(BadCredentialsException e) {
        log.error("user login error Exception with this {}" , e.getMessage());
        List<String> errors = new ArrayList<>();
        errors.add(e.getMessage());
        var error = ErrorResponse.builder()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .errors(getErrorsMap(errors))
                .message(errors.get(0))
                .time(LocalDateTime.now()).build();
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
    private ResponseEntity<?> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = "Error";
        return new ResponseEntity<>(message, httpStatus);
    }

}
