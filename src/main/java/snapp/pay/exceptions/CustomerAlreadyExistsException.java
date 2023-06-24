package snapp.pay.exceptions;


public class CustomerAlreadyExistsException extends CustomExceptionHandler {

    private static final long serialVersionUID = 1L;

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
