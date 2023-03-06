package page;

import base.BasePage;
import element.$;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import static element.$.LocatorType.ACCESSIBILITY_ID;

public class HistoryPage extends BasePage {

    private final $ backArrow = $(ACCESSIBILITY_ID, "Navigate up");
    private final $ moreOptions = $(ACCESSIBILITY_ID, "More options");

    public HistoryPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Wait History Page to load")
    public void waitPageToLoad() {
        Logs.debug("Wait History Page to load");
        waitPage(moreOptions, "Final Result");
    }

    @Override
    @Step("Verifying history page elements are displayed")
    public void verifyPage(){
        Logs.debug("Verifying history page elements are displayed");
        softAssert.assertTrue(backArrow.isDisplayed());
        softAssert.assertTrue(moreOptions.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on back arrow")
    public void clickOnBackArrow(){
        Logs.info("Clicking on back arrow");
        backArrow.click();
    }
}
