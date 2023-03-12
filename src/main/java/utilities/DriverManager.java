package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {

    private static AndroidDriver driver;

    public AndroidDriver buildLocalDriver(){
        Logs.debug("Building local driver");
        try{
            final var appiumUrl = "http://127.0.0.1:4723/wd/hub";

            final var fileApk = new File("src/main/resources/apk/calculator.apk");
            final var desiredCapabilities = new DesiredCapabilities();

            desiredCapabilities.setCapability("autoGrantPermissions",true);
            desiredCapabilities.setCapability("appWaitActivity", "com.google.android.calculator,com.android.calculator2.Calculator");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"mobile_emulator");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP,fileApk.getAbsolutePath());

            final var driver = new AndroidDriver(new URL(appiumUrl),desiredCapabilities);
            this.driver = driver;
            return driver;
        }catch (MalformedURLException malformedURLException){
            malformedURLException.printStackTrace();
            Logs.error("Failed building local driver");
            return null;
        }
    }

    public static AndroidDriver getStaticDriver() {
        return driver;
    }
}
