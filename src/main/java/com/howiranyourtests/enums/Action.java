package com.howiranyourtests.enums;

public enum Action {
    CLICK("click"),
    TYPE("type"),
    ASSERT("assert");

    private final String action;

    Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
