package common;

public class ServiceResult implements Result {

    private boolean isSuccess;
    private String message;
    private String errorMessage;

    public ServiceResult(boolean isSuccess, String message, String errorMessage) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
