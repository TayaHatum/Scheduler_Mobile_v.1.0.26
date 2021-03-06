package com.telran.fw;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.events.EventFiringWebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    AppiumDriver<MobileElement> driver;
    UserHelper userHelper;
    EventHelper event;
    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    DesiredCapabilities capabilities;


    public void start() throws IOException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "qa_26");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "com.example.svetlana.scheduler");
        capabilities.setCapability("appActivity", ".presentation.splashScreen.SplashScreenActivity");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app",
                "/Users/tayahatum/QAAppium/apk/v.0.0.3.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        logger.info("App version: " + getAppVersion());

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver = EventFiringWebDriverFactory.getEventFiringWebDriver(driver, new AppiumListener());


        userHelper = new UserHelper(driver);
        event= new EventHelper(driver);

    }

    public String getAppVersion() {

        return driver.findElement
                (By.xpath("//*[@resource-id='com.example.svetlana.scheduler:id/app_version_res']")).getText();
    }


    public void stop() {
        driver.quit();
    }

    public EventHelper event() {
        return event;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }
}
