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

public class PizzaPageTests extends TestBase {
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
}
