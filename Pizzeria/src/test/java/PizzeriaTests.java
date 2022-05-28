import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.PizzaPage;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PizzeriaTests extends TestBase{

    //MainPageTests=====================================================================================================
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
}
