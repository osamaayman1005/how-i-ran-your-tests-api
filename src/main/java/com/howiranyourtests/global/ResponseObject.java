package com.howiranyourtests.global;

public class ResponseObject {
    public String message;
    public Object data;

    public ResponseObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }
    public ResponseObject(String message) {
        this.message = message;
    }

}
