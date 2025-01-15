package com.howiranyourtests.global.exception;

public class ExecutionException extends RuntimeException{
    private final String screenshot;
    public ExecutionException(String message, String screenshot) {
        super(message);
        this.screenshot = screenshot;
    }
    public String getScreenshot() {
        return screenshot;
    }
}
