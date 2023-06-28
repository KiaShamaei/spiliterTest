package snapp.pay.exceptions;

/**
 * Custom error exception for Customer Not Found, catch with CustomExceptionHandler
 * @Author Kiarash Shamaei 2023-06-25
 */
public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
