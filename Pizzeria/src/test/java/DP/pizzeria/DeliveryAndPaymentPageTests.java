package DP.pizzeria;

import DP.TestBase;
import DP.pizzeria.pages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
