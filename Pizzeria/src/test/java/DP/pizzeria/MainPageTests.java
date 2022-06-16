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

public class MainPageTests extends TestBase {

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
}
