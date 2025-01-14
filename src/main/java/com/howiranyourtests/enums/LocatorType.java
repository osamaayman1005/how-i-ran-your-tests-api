package com.howiranyourtests.enums;

public enum LocatorType {
    ID("id"),
    CLASS_NAME("class"),
    XPATH("xpath"),
    NAME("name"),
    LINK_TEXT("linkText"),
    PARTIAL_LINK_TEXT("partialLinkText"),
    INNER_TEXT("innerText"),
    CSS_SELECTOR("css");
    private final String locatorType;

    LocatorType(String locatorType) {
        this.locatorType = locatorType;
    }

    public String getLocatorType() {
        return locatorType;
    }
}
