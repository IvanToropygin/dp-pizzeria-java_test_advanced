package DP.pizzeria;

import DP.TestBase;
import DP.pizzeria.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BonusProgramPageTests extends TestBase {
    //BonusPageTests====================================================================================================
    //успешное оформление карты с проверкой текста «Заявка отправлена, дождитесь, пожалуйста, оформления карты!».
    @Test
    public void successAlertTest(){
        //arrange
        var page = new BonusPage(driver, wait);
        page.open();
        //act
        page.userNameInput.sendKeys("Vasya");
        page.phoneInput.sendKeys("89201234567");
        page.getCardBtn.click();
        //assert
        var expectedTextInAlert = "Заявка отправлена, дождитесь, пожалуйста, оформления карты!";
        var alert = driver.switchTo().alert();
        var actualTextInAlert = alert.getText();
        Assertions.assertEquals(actualTextInAlert, expectedTextInAlert,"Не верный текст");
    }
}
