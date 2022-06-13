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

public class PizzeriaTests extends TestBase {

    //MainPageTests=====================================================================================================
    //переключение пиццы в слайдере посредством клика стрелочки вправо;
    @Test
    public void switchingPizzaInSliderArrowRightTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.navigationPanel.waitForLoadingAllPizzasInSlider();
        var lastPizzaInSliderMainPage = page.navigationPanel.getNameFourthPizzaInSlider();
        //act
        page.navigationPanel.clickRightArrowBtnInSlider();
        page.navigationPanel.waitForLoadingNextPizza();
        //assert
        var nextPizza = page.navigationPanel.getNameNextPizzaInSlider();
        Assertions.assertNotEquals(nextPizza, lastPizzaInSliderMainPage, "После нажатия на стрелку" +
                " вправо, пицца не изменилась");
    }

    //переключение пиццы в слайдере посредством клика стрелочки влево;
    @Test
    public void switchingPizzaInSliderArrowLeftTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.navigationPanel.waitForLoadingAllPizzasInSlider();
        var firstPizzaInSliderMainPage = page.navigationPanel.getNameFirstPizzaInSlider();
        //act
        page.navigationPanel.clickLeftArrowBtnInSlider();
        page.navigationPanel.waitForLoadingNextPizza();
        //assert
        var previousPizza = page.navigationPanel.getNamePreviousPizzaInSlider();
        Assertions.assertNotEquals(previousPizza, firstPizzaInSliderMainPage, "После нажатия на стрелку" +
                " влево, пицца не изменилась");
    }

    //наведение на картинку напитка с проверкой отображения ссылки «В КОРЗИНУ»;
    @Test
    public void firstProductInDrinks_InCartBtn_isDisplayedAfterHoverTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.navigationPanel.waitForLoadingAllPizzasInSlider();
        //act
        page.hoverFirstProductInDrinks();
        //assert
        Assertions.assertTrue(page.isFirstProductInDrinksDisplayed(), "не отобразилась кнопка 'В корзину', " +
                "после наведения мышью");
    }

    //переход на страницу десерта при клике по его картинке;
    @Test
    public void dessertCart_isDisplayed_AfterClickTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.navigationPanel.waitForLoadingAllPizzasInSlider();
        //act
        page.clickOnFirstDessert();
        //assert
        Assertions.assertTrue(page.isNameProductContainsText("Десерт"), "Не отобразилась карточка с десертом");
    }

    //отображение ссылки-стрелочки «Наверх» в правом нижнем углу сайта при скроллинге в самый низ сайта;
    @Test
    public void scrollDownPage_arrowUpIsDisplayedTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        //act
        page.scrollToDownPage();
        //assert
        Assertions.assertTrue(page.arrowUp.isDisplayed(), "НЕ отобразилась стрелка 'Вверх'");
    }

    //открытие ссылок на социальные сети из футера страницы в новой вкладке.
    @Test
    public void goToFacebookTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.scrollToDownPage();

        //act
        page.goFacebook();
        switchToFirstNewWindow();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllWindows().size(), "После открытия ссылки - окон не 2"),
                () ->Assertions.assertEquals("https://www.facebook.com/skillboxru", driver.getCurrentUrl(), "Перешли не на ту страницу")
        );
    }

    //открытие ссылок на социальные сети из футера страницы в новой вкладке.
    @Test
    public void goToVKTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.scrollToDownPage();

        //act
        page.goVK();
        switchToFirstNewWindow();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllWindows().size(), "После открытия ссылки - окон не 2"),
                () ->Assertions.assertEquals("https://vk.com/skillbox", driver.getCurrentUrl(), "Перешли не на ту страницу")
        );
    }

    //открытие ссылок на социальные сети из футера страницы в новой вкладке.
    @Test
    public void goToInstagramTest(){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.scrollToDownPage();

        //act
        page.goInstagram();
        switchToFirstNewWindow();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllWindows().size(), "После открытия ссылки - окон не 2"),
                () ->Assertions.assertEquals("https://www.instagram.com/skillbox.ru/", driver.getCurrentUrl(), "Перешли не на ту страницу")
        );
    }

    //PizzaPageTests====================================================================================================
    private static Stream<Arguments> selectValues(){
        return Stream.of(
                arguments("По популярности"),
                arguments("Последние"),
                arguments("По возрастанию цены"),
                arguments("По убыванию цены")
        );
    }

    //применение сортировки пицц;
    @ParameterizedTest
    @MethodSource("selectValues")
    public void selectionTest(String selectValue){
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        //act
        page.setSelect(selectValue);
        //assert
        Assertions.assertEquals(selectValue, page.getSelect(), "Отбор не соответствует выбранному");
    }

    //фильтрация пицц по цене;
    @Test
    public void isPriceFilterChanged_AfterMoveRightArrowFilterTest(){
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        var firstRightPrice = page.getRightPriceFilter();
        //act
        page.moveRightArrowFilter();
        //assert
        var actualRightPrice = page.getRightPriceFilter();
        Assertions.assertNotEquals(actualRightPrice, firstRightPrice, "Цена не изменилась");
    }

    //фильтрация пицц по цене;
    @Test
    public void isPriceFilterChanged_AfterMoveLeftArrowFilterTest(){
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        var firstLeftPrice = page.getLeftPriceFilter();
        //act
        page.moveLeftArrowFilter();
        //assert
        var actualLeftPrice = page.getLeftPriceFilter();
        Assertions.assertNotEquals(actualLeftPrice, firstLeftPrice, "Цена не изменилась");
    }

    //добавление пиццы в корзину.
    @Test
    public void isCartChanged_AfterClickInCartTest(){
        //arrange
        var page = new PizzaPage(driver, wait);
        page.open();
        var cartAmountFirst = page.navigationPanel.cart.getText();
        //act
        page.addToCartFirstPizzaBtnClick();
        //assert
        var cartAmountAfterAdding = page.navigationPanel.cart.getText();
        Assertions.assertNotEquals(cartAmountFirst, cartAmountAfterAdding, "Стоимость товаров в корзине не изменилась");
    }

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
    public void changeQuantityOfGoods(String quantityValue){
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
    public void increaseQuantityOfGoodsAndUpdate(){
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
    public void applyCouponAndUpdate(){
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
    public void transitionToPayment(){
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

    //CheckOutPageTests=================================================================================================
    //установка даты заказа;
    @Test
    public void inputOrderDate(){
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
    public void OrderingSuccess(){
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

    //NavigationMenuTests===============================================================================================
    private static Stream<Arguments> subMenu(){
        return Stream.of(
                arguments("Пицца", "http://pizzeria.skillbox.cc/product-category/menu/pizza/"),
                arguments("Десерты", "http://pizzeria.skillbox.cc/product-category/menu/deserts/"),
                arguments("Напитки", "http://pizzeria.skillbox.cc/product-category/menu/drinks/")
        );
    }

    //переход по всем разделам меню, включая «Меню» → «Пицца», «Меню» → «Десерты», «Меню» → «Напитки».
    @ParameterizedTest
    @MethodSource("subMenu")
    public void mainPageGoToElectronicsSubChapterTest(String name, String expectedUrl){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();

        //act
        page.navigationPanel.goToSubMenu(name);

        //assert
        Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(),
                        "Не перешли на нужную страницу");
    }

    //MyAccountTests====================================================================================================
    //загрузка файла.
    @Test
    public void addFile(){
        //arrange
        var page = new AutorizationPage(driver, wait);
        page.open();
        page.autorize("test@1900test.ru","12345qwe");
        page.editAccount.click();
        //act
        var filePath = System.getProperty("user.dir") + "\\src\\test\\java\\DP\\pizzeria\\pages\\screenshot.png";
        page.uploadFile(filePath);
        //assert
        Assertions.assertTrue(page.uploadPreview.isDisplayed(),"Не загрузился файл");
    }

    //BonusPageTests====================================================================================================
    //успешное оформление карты с проверкой текста «Заявка отправлена, дождитесь, пожалуйста, оформления карты!».
    @Test
    public void successAlert(){
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

    //DeliveryAndPaymentTests===========================================================================================
    //проверка, что на сайте в этом разделе отображается текст «Минимальная сумма заказа — 800 рублей».
    @Test
    public void checkMinimalPriceToOrderIsDisplayed(){
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
