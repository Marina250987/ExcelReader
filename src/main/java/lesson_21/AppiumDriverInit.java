package lesson_21;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverInit {

    public AppiumDriver getDriver() throws MalformedURLException {
        try {
            return initDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver: " + e.getMessage(), e);
        }
    }

    private AppiumDriver initDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setPlatformName("Android");
        options.setAppPackage("io.appium.android.apis");
        options.setAppActivity("io.appium.android.apis.ApiDemos");
        options.setPlatformVersion("16");
        options.setAutomationName("UiAutomator2");

        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid URL for Appium server: " + e.getMessage(), e);
        }
    }
}