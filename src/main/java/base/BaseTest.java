package base;

import com.github.javafaker.Faker;
import data.DataCaller;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import listeners.InvokeMethodListeners;
import listeners.SuiteListeners;
import listeners.TestListeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utilities.DriverManager;
import utilities.Logs;

@Listeners({TestListeners.class, SuiteListeners.class, InvokeMethodListeners.class})
public abstract class BaseTest {
    protected final String regression = "Regression";
    protected final String smoke = "Smoke";
    protected final String setup = "Setup";
    private AndroidDriver driver;
    protected DataCaller dataCaller = new DataCaller();
    protected Faker faker = new Faker();

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new DriverManager().buildLocalDriver();
        initPages(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown(){
        Logs.debug("Killing the driver");
        driver.quit();
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    protected void triggerDeeplink(String url){
        Logs.info("Triggering deeplink %s", url);
        driver.get(url);
    }

    @Step("Switching to web context")
    protected void switchWebContext() {
        Logs.debug("Switching to web context");
        final var contextNames = driver.getContextHandles();
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW
    }

    @Step("Switching to native app context")
    protected void switchNativeAppContext() {
        Logs.debug("Switching to native app context");
        final var contextNames = driver.getContextHandles();
        driver.context((String) contextNames.toArray()[0]); // set context to NATIVE_APP
    }

    public abstract void initPages(AndroidDriver driver);
}
