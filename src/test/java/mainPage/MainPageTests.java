package mainPage;

import base.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.HistoryPage;
import page.MainPage;

import static element.$.Orientation.VERTICAL;

public class MainPageTests extends BaseTest {
    private MainPage mainPage;
    private HistoryPage historyPage;

    @BeforeMethod
    @Step("Wait and verify page before start any test")
    public void waitAndVerifyPage(){
        mainPage.waitPageToLoad();
        mainPage.verifyPage();
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 1")
    public void Exercise1(){
        mainPage.clickOnMoreOptions();
        mainPage.verifyAllMoreOptions();
        mainPage.clickOnChooseTheme();
        mainPage.verifyAllChangeThemeOptions();
        mainPage.changeTheme("Dark"); //Light-Dark-SystemDefault
        mainPage.clickOnOkButton();
        mainPage.verifyPage();
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 2")
    public void Exercise2(){
        mainPage.clickOnOkDEG();
        mainPage.verifyRadianMode();
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 3")
    public void Exercise3(){
        final var operationSuma = dataCaller.getOperacionSuma();
        mainPage.clickOnDigit(operationSuma.getNumero1());
        mainPage.clickOnOperationAdd();
        mainPage.clickOnDigit(operationSuma.getNumero2());
        mainPage.clickOnEquals();
        mainPage.verifyResult(operationSuma.getResultado());
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 4")
    public void Exercise4(){
        final var number1 = faker.number().randomDouble(2,50,325);
        mainPage.inputNumber(String.valueOf(number1));
        mainPage.clickOnOperationMultiply();
        final var number2 = faker.number().randomDouble(2,50,325);
        mainPage.inputNumber(String.valueOf(number2));
        mainPage.clickOnEquals();
        final var result = number1*number2;
        mainPage.verifyResult(Double.toString(result));
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 5")
    public void Exercise5(){
        mainPage.clickOnMoreOptions();
        mainPage.verifyAllMoreOptions();
        mainPage.clickOnHistory();
        historyPage.waitPageToLoad();
        historyPage.verifyPage();
        historyPage.clickOnBackArrow();
        mainPage.waitPageToLoad();
        mainPage.verifyPage();
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 6")
    public void Exercise6(){
        mainPage.swipe(30,100, VERTICAL);
        historyPage.waitPageToLoad();
        historyPage.verifyPage();
    }

    @Test(groups = {smoke,regression})
    @Step("Exercise 7")
    public void Exercise7(){
        final var randomNumber = String.valueOf(faker.number().numberBetween(3000,25000));
        mainPage.inputNumber(randomNumber);
        mainPage.verifyFormula(randomNumber);
    }

    @Override
    public void initPages(AndroidDriver driver) {
        mainPage=new MainPage(driver);
        historyPage=new HistoryPage(driver);
    }
}
