package snapp.pay.exceptions;




/**
 * Custom error exception for Group Without Admin Exception, catch with CustomExceptionHandler
 * @Author Kiarash Shamaei 2023-06-25
 */
public class GroupWithoutAdminException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GroupWithoutAdminException(String message) {
        super(message);
    }
}
