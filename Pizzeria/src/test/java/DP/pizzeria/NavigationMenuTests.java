package DP.pizzeria;

import DP.TestBase;
import DP.pizzeria.pages.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class NavigationMenuTests extends TestBase {//NavigationMenuTests===============================================================================================
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
}
