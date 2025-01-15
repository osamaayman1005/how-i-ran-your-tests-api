package com.howiranyourtests.global.exception.response;

public class ExecutionErrorResponse extends ErrorResponse {
    private final String screenshot;
    public ExecutionErrorResponse(String message, String screenshot) {
        super(message);
        this.screenshot = screenshot;
    }
    public String getScreenshot() {
        return screenshot;
    }

}
