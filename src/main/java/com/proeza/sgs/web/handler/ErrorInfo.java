package com.proeza.sgs.web.handler;

public class ErrorInfo {

    private String url;
    private String message;

    public ErrorInfo(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorInfo [url=" + this.url + ", message=" + this.message + "]";
    }
}