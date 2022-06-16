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

public class MyAccountPageTests extends TestBase {
    //MyAccountTests====================================================================================================
    //загрузка файла.
    @Test
    public void addFileTest(){
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
}
