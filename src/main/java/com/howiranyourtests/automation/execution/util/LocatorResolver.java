package com.howiranyourtests.automation.execution.util;

import com.howiranyourtests.automation.script.model.ScriptAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class LocatorResolver {
    public WebElement resolve(WebDriver driver, ScriptAction action) {
        return switch (action.getLocatorType()) {
            case ID -> driver.findElement(By.id(action.getLocator()));
            case XPATH -> driver.findElement(By.xpath(action.getLocator()));
            case CLASS_NAME-> driver.findElement(By.className(action.getLocator()));
            case CSS_SELECTOR -> driver.findElement(By.cssSelector(action.getLocator()));
            case LINK_TEXT -> driver.findElement(By.linkText(action.getLocator()));
            case PARTIAL_LINK_TEXT -> driver.findElement(By.partialLinkText(action.getLocator()));
            case NAME -> driver.findElement(By.name(action.getLocator()));
            case INNER_TEXT -> driver.findElement(By.xpath("//*[text()='" + action.getLocator() + "']"));
        };
    }
}
