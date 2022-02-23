package com.imladyartist.accessloganalyzer;

public class Content {


    private String dateTime;
    private String operation;
    private String url;
    private String responseCode;
    private String size;
    private String duration;
    private String bearer;
    private String userAgent;

    public Content(String dateTime, String operation, String url, String responseCode, String size, String duration, String bearer, String userAgent) {
        this.dateTime = dateTime;
        this.operation = operation;
        this.url = url;
        this.responseCode = responseCode;
        this.size = size;
        this.duration = duration;
        this.bearer = bearer;
        this.userAgent = userAgent;
    }


    public String getDateTime() {
        return dateTime;
    }

    public String getOperation() {
        return operation;
    }

    public String getUrl() {
        return url;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getSize() {
        return size;
    }

    public String getDuration() {
        return duration;
    }

    public String getBearer() {
        return bearer;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
