package edu.sumdu.tss.elephant.helper.exception;

public class HttpError400 extends HttpException {

    private static final int HTTP_STATUS_CODE = 400;

    public HttpError400() {
        super();
    }

    public HttpError400(Exception ex) {
        super(ex);
    }

    public HttpError400(String message) {
        super(message);
    }

    public Integer getCode() {
        return HTTP_STATUS_CODE;
    }

}
