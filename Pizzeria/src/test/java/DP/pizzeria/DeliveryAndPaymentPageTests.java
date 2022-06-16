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

public class DeliveryAndPaymentPageTests extends TestBase {
    //DeliveryAndPaymentTests===========================================================================================
    //проверка, что на сайте в этом разделе отображается текст «Минимальная сумма заказа — 800 рублей».
    @Test
    public void checkMinimalPriceToOrderIsDisplayedTest(){
        //arrange
        var page = new DeliveryAndPaymentPage(driver, wait);
        page.open();
        page.switchToFrame();
        //assert
        var expectedText = "Минимальная сумма заказа 800 рублей.";
        var actualText = page.secondParagraph.getText();
        Assertions.assertEquals(actualText, expectedText,"Не верный текст");
    }
}
