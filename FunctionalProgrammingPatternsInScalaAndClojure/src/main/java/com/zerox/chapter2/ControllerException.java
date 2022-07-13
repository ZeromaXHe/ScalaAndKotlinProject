package com.zerox.chapter2;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/7/13 11:12
 */
public class ControllerException extends RuntimeException {
    private static final long serialVersionUID = -3643040165977468050L;

    private Integer statusCode;

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}
