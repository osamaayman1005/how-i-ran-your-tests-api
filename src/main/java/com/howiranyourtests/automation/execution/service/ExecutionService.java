package com.howiranyourtests.automation.execution.service;

import com.howiranyourtests.global.exception.ExecutionException;
import com.howiranyourtests.automation.execution.util.ActionResolver;
import com.howiranyourtests.automation.execution.util.DriverManager;
import com.howiranyourtests.automation.execution.util.LocatorResolver;
import com.howiranyourtests.automation.script.model.Script;
import com.howiranyourtests.automation.script.model.ScriptAction;
import com.howiranyourtests.automation.script.service.ScriptService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.Comparator;

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
        long startTime = System.currentTimeMillis();  // Record the start time

        try {
            driver.get(script.getUrl());  // Navigate to the script URL

            // Execute all actions associated with the script

            for (ScriptAction action : script.getActions().stream()
                    .sorted(Comparator.comparingInt(ScriptAction::getOrder))
                    .toList()) {
                executeAction(driver, action);
            }

            long endTime = System.currentTimeMillis();  // Record the end time
            return "Test passed in " + (endTime - startTime) + " ms";  // If all actions pass, return pass
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();  // Record the end time in case of errors
            throw new ExecutionException("fail (Execution time: " + (endTime - startTime) + " ms) "
                    + "logs: " + e.getMessage(), driverManager.captureScreenshotAsBase64(driver));

            // Return fail with execution time
        } finally {
            driver.quit();  // Always close the browser after execution
        }
    }

    // Execute a single action
    public void executeAction(WebDriver driver, ScriptAction action) throws Exception {
        WebElement element = locatorResolver.resolve(driver, action);
        actionResolver.resolve(action, element);


    }

}
