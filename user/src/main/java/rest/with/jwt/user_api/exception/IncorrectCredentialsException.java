package rest.with.jwt.user_api.exception;

public class IncorrectCredentialsException extends RuntimeException{
    public IncorrectCredentialsException(String message){
        super(message);
    }
}
