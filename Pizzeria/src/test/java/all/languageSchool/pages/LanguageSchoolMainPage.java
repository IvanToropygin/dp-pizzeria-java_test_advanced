package all.languageSchool.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguageSchoolMainPage {
    @FindBy(css = ".js-fileName img[src*='yes.png']")
    public WebElement successCheck;
    @FindBy(css = ".js-fileName img[src*='no.png']")
    public WebElement noSuccessCheck;
    @FindBy(id = "uploadInput")
    public WebElement fileNameInput;
    @FindBy(className = "error")
    private WebElement error;

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor jsExecutor;

    String url = "https://lm.skillbox.cc/qa_tester/module08/homework1/";

    public LanguageSchoolMainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        jsExecutor = (JavascriptExecutor)driver;
        PageFactory.initElements(driver,this);
    }

    public void open(){
        driver.navigate().to(url);
    }

    public String getPageUrl(){ return url; }

    public void uploadFile(String filePath){
        jsExecutor.executeScript("document.getElementById(\"uploadInput\").setAttribute(\"class\", \"bla\");");
        fileNameInput.sendKeys(filePath);
    }

    public boolean isErrorHasText(String errorText){
        return error.getText() == errorText;
    }

    public void scrollToUploadFile(){
        for (var i = 0; i < 200; i++){
            driver.findElement(By.cssSelector("html")).sendKeys(Keys.ARROW_DOWN);
        }
    }
}
