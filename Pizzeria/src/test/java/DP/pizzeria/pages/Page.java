package DP.pizzeria.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(tagName = "html")
    private WebElement page;
    @FindBy(id = "ak-top")
    public WebElement arrowUp;

    private String url = "http://pizzeria.skillbox.cc/";
    private String subUrl;

    public Page(WebDriver driver, WebDriverWait wait, String subUrl){
        this.driver = driver;
        this.wait = wait;
        this.subUrl = subUrl;
        PageFactory.initElements(driver, this);
    }

    public Page(WebDriver driver, WebDriverWait wait){
        this(driver, wait, "");
    }

    public void open(){driver.navigate().to(getPageUrl());}

    public String getPageUrl(){return url + subUrl;}

    public void scrollToDownPage(){
        for (var i = 0; i < 50; i++){page.sendKeys(Keys.ARROW_DOWN);};
        wait.until(ExpectedConditions.visibilityOf(arrowUp));
    }

}
