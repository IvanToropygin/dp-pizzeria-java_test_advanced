package all.parrotsNames;

import all.TestBase;
import all.parrotsNames.pages.ParrotsMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParrotsMainPageTests extends TestBase {
    @Test
    public void mainPage_OpenSkillboxAndClose_FormIsDisplayed(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.switchToFrame();
        page.skillboxLink.click();
        switchToFirstNewWindow();
        driver.close();
        switchToWindow(initialWindow);
        page.switchToFrame();

        //assert
        Assertions.assertTrue(page.form.isDisplayed(), "Форма не отобразилась");
    }

    @Test
    public void checkError_EnterEmailTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.pickUpNameBtn.click();
        page.switchToFrame();
        page.skillboxLink.click();
        page.switchToParentFrame();

        //assert
        Assertions.assertTrue(page.errorInputEmail.isDisplayed(), "Ошибка не отображается");
    }

    @Test
    public void checkHeaderIsDisplayedAfterClickPickUoButtonTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();
        page.switchToParentFrame();

        //assert
        Assertions.assertTrue(page.header.isDisplayed(), "Хедэр не отображается");
    }

    @Test
    public void oneWindowAfterSkillboxLinkOpenAndCloseNewWindowTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.switchToFrame();
        page.skillboxLink.click();
        switchToFirstNewWindow();
        driver.close();
        switchToWindow(initialWindow);

        //assert
        Assertions.assertEquals(1, getAllwindows().size(), "Форма не отобразилась");
    }

    private static Stream<Arguments> SocialLinks(){
        return Stream.of(
                arguments(1, "https://www.facebook.com/skillboxru"),
                arguments(2, "https://vk.com/skillbox_education"),
                arguments(3, "https://twitter.com/skillboxru"),
                arguments(4, "https://www.instagram.com/skillbox.ru")
        );
    }

    @ParameterizedTest
    @MethodSource("SocialLinks")
    public void mainPageGoToSocialLinksTest(int index, String expectedUrl){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.switchToFrame();
        page.goToSocialLinks(index);
        switchToFirstNewWindow();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, getAllwindows().size(), "После открытия ссылки - окон не 2"),
                () ->Assertions.assertEquals(expectedUrl, driver.getCurrentUrl(), "Перешли не на ту страницу")
        );
    }

    @Test
    public void mainPageOpened_HeaderIsDisplayedTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        //act
        page.open();
        //assert
        Assertions.assertTrue(page.header.isDisplayed(), "Хедэр не отображается");
    }

    @Test
    public void mainPageOpened_TitleIsDisplayedTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        //act
        page.open();
        page.switchToFrame();
        //assert
        Assertions.assertTrue(page.header.isDisplayed(), "Хедэр не отображается");
    }

    @Test
    public void errorShow_EmailIsEmptyTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.pickUpNameBtn.click();

        //assert
        Assertions.assertTrue(page.errorInputEmail.isDisplayed(), "Ошибка не отображается");
    }

    @Test
    public void errorDontShow_EmailIsCorrectTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();

        //assert
        Assertions.assertFalse(page.errorInputEmail.isDisplayed(), "Ошибка отображается");
    }

    @Test
    public void successMessageTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();

        //act
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();

        //assert
        String expectedText = "Хорошо, мы пришлём имя для вашего мальчика на e-mail:";
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.successMessage.isDisplayed(), "Сообщение об отправке имени не отображается"),
                () -> Assertions.assertEquals(expectedText, page.successMessage.getText(), "Не верный текст")
        );
    }

    @Test
    public void changeEmail_successTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();

        //act
        page.anotherEmailLink.click();
        driver.switchTo().alert().accept();

        //assert
        var expectedText = "E-mail успешно изменён на";
        Assertions.assertTrue(driver.switchTo().alert().getText().contains(expectedText), "Не отобразился нужный текст");
    }

    @Test
    public void changeEmailcancel_linkAnotherEmailNotDisplayedAndInputIsEmptyTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();

        //act
        page.anotherEmailLink.click();
        driver.switchTo().alert().dismiss();

        //assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.emailInput.getText().isEmpty(), "Поле ввода не пустое"),
                () -> Assertions.assertFalse(page.anotherEmailLink.isDisplayed())
        );
    }

    @Test
    public void changeEmail_alertTitleHasTextTest(){
        //arrange
        var page = new ParrotsMainPage(driver, wait);
        page.open();
        page.switchToFrame();
        page.emailInput.sendKeys("test@test.ru");
        page.pickUpNameBtn.click();

        //act
        page.anotherEmailLink.click();

        //assert
        var titleInAlert = driver.switchTo().alert().getText();
        driver.switchTo().alert().dismiss();
        var expectedText = "Укажите другой e-mail";
        Assertions.assertEquals(expectedText, titleInAlert, "Не отобразился нужный текст");
    }
}
