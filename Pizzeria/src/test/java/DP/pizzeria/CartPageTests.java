package DP.pizzeria;

import DP.TestBase;
import DP.pizzeria.pages.AutorizationPage;
import DP.pizzeria.pages.CartPage;
import DP.pizzeria.pages.PizzaPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CartPageTests extends TestBase {
    //CartPageTests=====================================================================================================
    private static Stream<Arguments> quantity(){
        return Stream.of(
                arguments("2"),
                arguments("0")
        );
    }
    //увеличение/уменьшение количества товара;
    @ParameterizedTest
    @MethodSource("quantity")
    public void changeQuantityOfGoodsTest(String quantityValue){
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var cartPage = new CartPage(driver, wait);
        cartPage.open();
        var quantityBeforeChange = cartPage.quantity.getAttribute("value");
        //act
        cartPage.quantity.sendKeys(Keys.DELETE);
        cartPage.quantity.sendKeys(quantityValue);
        //assert
        var quantityAfterChange = cartPage.quantity.getAttribute("value");
        Assertions.assertNotEquals(quantityAfterChange, quantityBeforeChange, "Количество товаров в корзине не изменилась");
    }

    //обновление корзины после изменения содержимого (количества товаров);
    @Test
    public void increaseQuantityOfGoodsAndUpdateTest(){
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var cartPage = new CartPage(driver, wait);
        cartPage.open();
        var totalPriceBeforeChange = cartPage.orderTotal.getText();
        //act
        cartPage.quantity.sendKeys(Keys.DELETE);
        cartPage.quantity.sendKeys("2");
        cartPage.updateCart();
        //assert
        var totalPriceAfterChange = cartPage.orderTotal.getText();
        Assertions.assertNotEquals(totalPriceBeforeChange, totalPriceAfterChange, "Сумма корзины не изменилась");
    }

    //применение промокода (из раздела «Акции»).
    @Test
    public void applyCouponAndUpdateTest(){
        //arrange
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var cartPage = new CartPage(driver, wait);
        cartPage.open();
        var totalPriceBeforeChange = cartPage.orderTotal.getText();
        //act
        cartPage.enterCouponAndApplyCouponBtnClick("GIVEMEHALYAVA");
        //assert
        var totalPriceAfterChange = cartPage.orderTotal.getText();
        Assertions.assertNotEquals(totalPriceBeforeChange, totalPriceAfterChange, "Сумма корзины не изменилась");
    }

    //переход к оплате (пользователь предварительно авторизован на сайте);
    @Test
    public void transitionToPaymentTest(){
        //arrange
        var autorizationPage = new AutorizationPage(driver, wait);
        autorizationPage.open();
        autorizationPage.autorize("test@1900test.ru","12345qwe");
        var pizzaPage = new PizzaPage(driver, wait);
        pizzaPage.open();
        pizzaPage.addToCartFirstPizzaBtnClick();
        var cartPage = new CartPage(driver, wait);
        cartPage.open();
        //act
        cartPage.checkoutBtn.click();
        //assert
        Assertions.assertTrue(driver.getCurrentUrl().contains("checkout"), "Не перешли на страницу Оформления");
    }
}
