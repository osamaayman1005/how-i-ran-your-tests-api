package com.howiranyourtests.execution.util;

import com.howiranyourtests.script.model.ScriptAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class LocatorResolver {
    public WebElement resolve(WebDriver driver, ScriptAction action) {
        return switch (action.getLocatorType()) {
            case "id" -> driver.findElement(By.id(action.getLocator()));
            case "xpath" -> driver.findElement(By.xpath(action.getLocator()));
            case "className" -> driver.findElement(By.className(action.getLocator()));
            default -> throw new IllegalArgumentException("Invalid locator type: " + action.getLocatorType());
        };
    }
}
