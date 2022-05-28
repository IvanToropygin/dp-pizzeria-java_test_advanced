package all.languageSchool;

import all.TestBase;
import all.languageSchool.pages.LanguageSchoolMainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LanguageSchoolMainPageTests extends TestBase {
    @Test
    public void mainPage_UploadFile_Success(){
        //arrange
        var page = new LanguageSchoolMainPage(driver, wait);
        page.open();

        //act
        var filePath = System.getProperty("user.dir") + "\\src\\test\\java\\all\\languageSchool\\screenshot.png";
        page.uploadFile(filePath);

        //assert
        Assertions.assertTrue(page.successCheck.isDisplayed(), "Зеленая галка не отобразилась");
    }

    @Test
    public void mainPage_UploadFile_More5Mb_Success(){
        //arrange
        var page = new LanguageSchoolMainPage(driver, wait);
        page.open();
        page.scrollToUploadFile();

        //act
        var filePath = System.getProperty("user.dir") + "\\src\\test\\java\\all\\languageSchool\\more5Mb.jpg";
        page.uploadFile(filePath);

        //assert
        var expectedText = "Размер файла не должен превышать 5 Мб";
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.noSuccessCheck.isDisplayed(), "Красный крестик не отобразился"),
                () -> Assertions.assertTrue(page.isErrorHasText(expectedText), "Отобразился не верный текст ошибки")
        );
    }
}
