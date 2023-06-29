package snapp.pay.exceptions;





/**
 * Custom error exception for Group Not Found Exception, catch with CustomExceptionHandler
 * @Author Kiarash Shamaei 2023-06-25
 */
public class BillNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BillNotFoundException(String message) {
        super(message);
    }
}
