package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class FileManager {
    private final String allureReportsPath = "target/allure-results";
    private final String screenshotsPath = "src/test/resources/screenshots";
    public static AndroidDriver staticDriver;

    public FileManager deleteTestEvidence() {
        try {
            Logs.debug("Deleting screenshots directory");
            FileUtils.deleteDirectory(new File(screenshotsPath));
        } catch (IOException ioException) {
            Logs.error("Failed deleting folder");
            Logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    public FileManager deleteAllureReports() {
        try {
            Logs.debug("Deleting screenshots directory");
            FileUtils.deleteDirectory(new File(allureReportsPath));
        } catch (IOException ioException) {
            Logs.error("Failed deleting folder");
            Logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    public FileManager redirectStdErr() {
        Logs.debug("Redirecting stderr");
        final File file = new File("src/test/resources/logs/stderr.log");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        final var ps = new PrintStream(fos);
        System.setErr(ps);
        return this;
    }

    public FileManager getScreenshot(WebDriver driver, String screenshotName) {
        Logs.debug("Taking screenshot");
        final var screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        final var path = String.format("%s/screenshot-%s.png", screenshotsPath, screenshotName);
        try {
            FileUtils.copyFile(screenshotFile, new File(path));
        } catch (IOException ioException) {
            Logs.error("Failed creating screenshots");
            Logs.error(ioException.getLocalizedMessage());
        }
        return this;
    }

    @Attachment(value = "failureScreenshot", type = "image/png")
    public byte[] getAllureScreenshot(WebDriver staticDriver) {
        Logs.debug("Taking allure screenshot");
        return ((TakesScreenshot) FileManager.staticDriver).getScreenshotAs(OutputType.BYTES);
    }
}