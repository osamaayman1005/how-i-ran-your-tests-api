package com.howiranyourtests.execution.util;

import com.howiranyourtests.enums.Action;
import com.howiranyourtests.script.model.ScriptAction;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActionResolver {

    @Value( "${assertion.maximum-retires:3}")
    private int maximumRetries;

    public void resolve(ScriptAction action, WebElement element) throws Exception {
        switch (action.getAction()) {
            case Action.CLICK:
                element.click();
                break;
            case Action.TYPE:
                element.sendKeys(action.getValue());
                break;
            case Action.ASSERT:
                performAssertion(action, element);
                break;
        }
    }

    private void performAssertion(ScriptAction action, WebElement element) throws Exception {
        String elementText = null;
        int i = 0;
        while (i < maximumRetries) {
            elementText = element.getText();
            if (elementText.equals(action.getValue())) {
                return;  // Assertion passed
            }
            i++;
            Thread.sleep(1000);  // Wait for 1 second before retrying

        }

        throw new Exception("Assertion failed at " + action.getId()
                + " Expected: " + action.getValue() + " Actual: " + elementText);
    }
}
