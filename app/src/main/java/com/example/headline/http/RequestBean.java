package com.example.headline.http;



public class RequestBean {
    String url="";
    String value = "";
    String method = "GET";

    public RequestBean(String url) {
        this.url = url;
    }

    public RequestBean(String url, String value, String method) {
        this.url = url;
        this.value = value;
        this.method = method;
    }
}
