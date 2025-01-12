package com.howiranyourtests.execution.util;

import com.howiranyourtests.script.model.ScriptAction;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class ActionResolver {
    public void resolve(ScriptAction action, WebElement element) throws Exception {
        switch (action.getAction()) {
            case "click":
                element.click();
                break;
            case "enter-text":
                element.sendKeys(action.getValue());
                break;
            case "assert-text":
                String elementText = element.getText();
                if (!elementText.equals(action.getValue())) {
                    throw new Exception( "Assertion failed at " + action);  // Assert failed if text does not match
                }
                break;
            default:
                throw new Exception("Unsupported Action " + action) ;  // Unrecognized action
        }
    }
}
