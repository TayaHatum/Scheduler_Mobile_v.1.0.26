package com.telran.fw;

import com.google.common.io.Files;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    AppiumDriver driver;;

    public HelperBase(AppiumDriver driver) {
        this.driver = driver;
    }

    public void typeByCss(By locator, String text) {
        if(text!=null){
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }

    }



    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if(text!=null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

   public void takeScreenshot(String pathToFile){
       File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
       File screenshot = new
               File(pathToFile);

       try {
           Files.copy(tmp, screenshot);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
