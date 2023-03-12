package page;

import base.BasePage;
import element.$;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import utilities.Logs;

import static element.$.LocatorType.*;

public class MainPage extends BasePage {

    private final $ moreOptions = $(ACCESSIBILITY_ID, "More options");
    private final $ historyOptions = $(UIAUTOMATOR2,"text(\"History\")");
    private final $ chooseThemeOptions = $(UIAUTOMATOR2,"text(\"Choose theme\")");
    private final $ sendFeedbackOptions = $(UIAUTOMATOR2,"text(\"Send feedback\")");
    private final $ helpOptions = $(UIAUTOMATOR2,"text(\"Help\")");
    private final $ lightTheme = $(UIAUTOMATOR2,"text(\"Light\")");
    private final $ darkTheme = $(UIAUTOMATOR2,"text(\"Dark\")");
    private final $ systemDefaultTheme = $(UIAUTOMATOR2,"text(\"System default\")");
    private final $ chooseThemeOkButton = $(UIAUTOMATOR2,"text(\"OK\")");
    private final $ degreeModeOptions = $(ACCESSIBILITY_ID, "degree mode");
    private final $ radianModeOptions = $(ACCESSIBILITY_ID, "radian mode");
    private final $ switchToDegreeOptions = $(ACCESSIBILITY_ID, "switch to degrees");
    private final $ operationAdd = $(ACCESSIBILITY_ID, "plus");
    private final $ operationMultiply = $(ID,"com.google.android.calculator:id/op_mul");
    private final $ equals = $(ACCESSIBILITY_ID, "equals");
    private final $ finalResult = $(ID,"com.google.android.calculator:id/result_final");
    private final $ formulaInput = $(ACCESSIBILITY_ID, "No formula");

    public MainPage(AndroidDriver driver) {
        super(driver);
    }

    @Override
    @Step("Wait Main Page to load")
    public void waitPageToLoad() {
        waitPage(moreOptions, "Main Page");
    }

    @Override
    @Step("Verifying main page elements are displayed")
    public void verifyPage(){
        Logs.info("Verifying main page elements are displayed");
        softAssert.assertTrue(moreOptions.isDisplayed());
        softAssert.assertTrue(degreeModeOptions.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on More Options (3 dots)")
    public void clickOnMoreOptions(){
        Logs.info("Clicking on More Options");
        moreOptions.click();
    }

    @Step("Verifying all More Options are displayed")
    public void verifyAllMoreOptions(){
        Logs.info("Verifying all More Options are displayed");
        softAssert.assertTrue(historyOptions.isDisplayed());
        softAssert.assertTrue(chooseThemeOptions.isDisplayed());
        softAssert.assertTrue(sendFeedbackOptions.isDisplayed());
        softAssert.assertTrue(helpOptions.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on Choose Theme")
    public void clickOnChooseTheme(){
        Logs.info("Clicking on Choose Theme");
        chooseThemeOptions.click();
    }

    @Step("Clicking on History")
    public void clickOnHistory(){
        Logs.info("Clicking on History");
        historyOptions.click();
    }

    @Step("Verifying all Change Theme options are displayed")
    public void verifyAllChangeThemeOptions(){
        Logs.info("Verifying all Change Theme options are displayed");
        softAssert.assertTrue(lightTheme.isDisplayed());
        softAssert.assertTrue(darkTheme.isDisplayed());
        softAssert.assertTrue(systemDefaultTheme.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Changing Theme")
    public void changeTheme(String theme){
        Logs.info(String.format("Changing Theme to %s",theme));
        if(theme.equals("Light")){
            lightTheme.click();
        } else if (theme.equals("Dark")) {
            darkTheme.click();
        }else {systemDefaultTheme.click();}
    }

    @Step("Clicking on Ok button")
    public void clickOnOkButton(){
        Logs.info("Clicking on Ok button");
        chooseThemeOkButton.click();
    }

    @Step("Clicking on DEG")
    public void clickOnOkDEG(){
        Logs.info("Clicking on DEG");
        degreeModeOptions.click();
    }

    @Step("Verifying Radian mode is set")
    public void verifyRadianMode(){
        Logs.info("Verifying Radian mode is set");
        softAssert.assertTrue(radianModeOptions.isDisplayed());
        softAssert.assertTrue(switchToDegreeOptions.isDisplayed());
        softAssert.assertAll();
    }

    @Step("Clicking on digit")
    public void clickOnDigit(String number){
        Logs.info("Clicking on digit");
        final var digit = new AppiumBy.ByAndroidUIAutomator(String.format("text(\"%s\")",number));
        driver.findElement(digit).click();
    }

    @Step("Clicking on plus")
    public void clickOnOperationAdd(){
        Logs.info("Clicking on plus");
        operationAdd.click();
    }

    @Step("Clicking on equals")
    public void clickOnEquals(){
        Logs.info("Clicking on equals");
        equals.click();
    }

    @Step("Verifying result")
    public void verifyResult(String result){
        Logs.info("Verifying result");
        waitPage(finalResult, "Final Result");
        softAssert.assertEquals(result,finalResult.getText());
    }

    @Step("Verifying formula")
    public void verifyFormula(String result){
        Logs.info("Verifying formula");
        waitPage(formulaInput, "Formula");
        softAssert.assertEquals(result,formulaInput.getText());
    }

    @Step("Input number")
    public void inputNumber(String number){
        Logs.info(String.format("Input number: %s",number));
        final var arrayDigits = number.toCharArray();
        for(var x=0;x<arrayDigits.length;x++){
            formulaInput.sendKeys(String.valueOf(arrayDigits[x]));
        }
    }

    @Step("Clicking on multiply")
    public void clickOnOperationMultiply(){
        Logs.info("Clicking on multiply");
        operationMultiply.click();
    }
}
