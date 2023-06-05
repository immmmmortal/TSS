package edu.sumdu.tss.elephant.helper.exception;

public class NotFoundException extends HttpError400 {

    private static final int HTTP_STATUS_CODE = 404;

    public NotFoundException(String message) {
        super(message);
    }

    public Integer getCode() {
        return HTTP_STATUS_CODE;
    }

}
