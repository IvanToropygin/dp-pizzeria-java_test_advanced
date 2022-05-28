package pages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class PizzeriaTests extends TestBase {

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

    private static Stream<Arguments> SocialLinks(){
        return Stream.of(
                arguments(3, "https://www.facebook.com/skillboxru"),
                arguments(4, "https://vk.com/skillbox"),
                arguments(5, "https://www.instagram.com/skillbox.ru/")
        );
    }

    @ParameterizedTest
    @MethodSource("SocialLinks")
    public void mainPageGoToSocialLinksTest(int index, String expectedUrl){
        //arrange
        var page = new MainPage(driver, wait);
        page.open();
        page.scrollToDownPage();

        //act
        page.goToSocialLinks(index);
        switchToFirstNewWindow();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllwindows().size(), "После открытия ссылки - окон не 2"),
                () ->Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Перешли не на ту страницу")
        );
    }
}
