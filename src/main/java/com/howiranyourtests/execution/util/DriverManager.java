package com.howiranyourtests.execution.util;

import com.howiranyourtests.global.exception.GenericApiException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class DriverManager {

    @Value("${webdriver.implicit.wait:10}") // Default to 10 seconds if not specified
    private int implicitWait;

    public WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu", "--headless", "--no-sandbox"); // Add any required options

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); // Set implicit wait

        return driver;
    }

    public String captureScreenshotAsBase64(WebDriver driver) {
        try {
            // Take the screenshot as a Base64 string
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            throw new GenericApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}