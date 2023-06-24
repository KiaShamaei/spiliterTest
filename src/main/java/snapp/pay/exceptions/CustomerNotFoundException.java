package snapp.pay.exceptions;


public class CustomerNotFoundException extends CustomExceptionHandler {

    private static final long serialVersionUID = 1L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
