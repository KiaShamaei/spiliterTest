package snapp.pay.exceptions;


public class GroupNotFoundException extends CustomExceptionHandler {

    private static final long serialVersionUID = 1L;

    public GroupNotFoundException(String message) {
        super(message);
    }
}
