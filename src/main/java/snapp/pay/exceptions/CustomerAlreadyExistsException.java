package snapp.pay.exceptions;


/**
 * Custom error exception for CustomerAlready exist, catch with CustomExceptionHandler
 * @Author Kiarash Shamaei 2023-06-25
 */
public class CustomerAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
