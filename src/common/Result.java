package common;

public interface Result {
    boolean isSuccess();
    String getErrorMessage();
    String getMessage();
}
