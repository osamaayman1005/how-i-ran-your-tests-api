package com.howiranyourtests.execution.service;

import com.howiranyourtests.execution.util.ActionResolver;
import com.howiranyourtests.execution.util.DriverManager;
import com.howiranyourtests.execution.util.LocatorResolver;
import com.howiranyourtests.script.model.Script;
import com.howiranyourtests.script.model.ScriptAction;
import com.howiranyourtests.script.service.ScriptService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

@Service
public class ExecutionService {

    private final DriverManager driverManager;
    private final LocatorResolver locatorResolver;
    private final ActionResolver actionResolver;
    private final ScriptService scriptService;

    public ExecutionService(DriverManager driverManager, LocatorResolver locatorResolver, ActionResolver actionResolver, ScriptService scriptService) {
        this.driverManager = driverManager;
        this.locatorResolver = locatorResolver;
        this.actionResolver = actionResolver;
        this.scriptService = scriptService;
    }



    public String executeScript(Long scriptId) throws Exception {
        Script script = scriptService.getScriptById(scriptId).orElseThrow(Exception::new);
        WebDriver driver = driverManager.getDriver();
        try {
            driver.get(script.getUrl());  // Navigate to the script URL

            // Execute all actions associated with the script
            for (ScriptAction action : script.getActions()) {
                if (!executeAction(driver, action)) {
                    return "fail";  // If any action fails, return fail
                }
            }

            return "pass";  // If all actions pass, return pass
        } catch (Exception e) {
            return "fail";  // Return fail in case of any error
        } finally {
            driver.quit();  // Always close the browser after execution
        }
    }

    // Execute a single action
    public boolean executeAction(WebDriver driver, ScriptAction action) {
        try {
            WebElement element = locatorResolver.resolve(driver, action);
            actionResolver.resolve(action, element);

            return true;  // If no issues, the action was successful
        } catch (Exception e) {
            return false;  // Return false if there was any issue executing the action
        }
    }

}
