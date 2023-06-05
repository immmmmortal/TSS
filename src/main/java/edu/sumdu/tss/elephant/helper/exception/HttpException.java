package edu.sumdu.tss.elephant.helper.exception;

public class HttpException extends RuntimeException {
    private static final String DEFAULT_ICON = "bug";
    private static final int HTTP_STATUS_CODE = 500;

    public HttpException() {
        super();
    }

    public HttpException(Exception ex) {
        super(ex);
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Exception ex) {
        super(message, ex);
    }

    public Integer getCode() {
        return HTTP_STATUS_CODE;
    }

    public String getIcon() {
        return DEFAULT_ICON;
    }

}
