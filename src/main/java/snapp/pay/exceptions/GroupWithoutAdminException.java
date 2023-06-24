package snapp.pay.exceptions;


public class GroupWithoutAdminException extends CustomExceptionHandler {

    private static final long serialVersionUID = 1L;

    public GroupWithoutAdminException(String message) {
        super(message);
    }
}
