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

public class OrderingPageTests extends TestBase {
    //CheckOutPageTests=================================================================================================
    //установка даты заказа;
    @Test
    public void inputOrderDateTest(){
        //arrange
        var autorizationPage = new AutorizationPage(driver, wait);
        autorizationPage.open();
        autorizationPage.autorize("test@1900test.ru","12345qwe");
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.open();
        var orderDateBefore = checkoutPage.order_date.getAttribute("value");
        //act
        checkoutPage.order_date.sendKeys("15.07.2022");
        //assert
        var orderDateAfter = checkoutPage.order_date.getAttribute("value");
        Assertions.assertNotEquals(orderDateAfter, orderDateBefore, "Дата не изменилась");
    }

    //успешное оформление заказа с оплатой наличными.
    @Test
    public void OrderingSuccessTest(){
        //arrange
        var autorizationPage = new AutorizationPage(driver, wait);
        autorizationPage.open();
        autorizationPage.autorize("test@1900test.ru","12345qwe");
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var checkoutPage = new CheckoutPage(driver, wait);
        checkoutPage.open();
        //act
        checkoutPage.order_date.sendKeys("15.07.2022");
        checkoutPage.firstNameInput.sendKeys("Vasya");
        checkoutPage.lastNameInput.sendKeys("Vetrov");
        checkoutPage.addressInput.sendKeys("str. Lenina, 159");
        checkoutPage.cityInput.sendKeys("Moscow");
        checkoutPage.stateInput.sendKeys("Moscow");
        checkoutPage.postcodeInput.sendKeys("123456");
        checkoutPage.phoneInput.sendKeys("89123456789");
        checkoutPage.payAfterDelivery.click();
        checkoutPage.termsAndConditionsCheckbox.click();
        checkoutPage.placeOrderBtn.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(checkoutPage.successMessage.isDisplayed(), "Не отобразилось " +
                        "сообщение об успешном заказе"),
                () ->Assertions.assertEquals("Спасибо! Ваш заказ был получен.", checkoutPage.successMessage
                        .getText(), "Не верный текст сообщения")
        );
    }
}
