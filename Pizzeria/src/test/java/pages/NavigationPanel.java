package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPanel {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(css = ".slick-track > li:nth-of-type(4)")
    private WebElement previousPizzaInSlider;
    @FindBy(css = ".slick-track > li:nth-of-type(5)")
    private WebElement firstPizzaInSlider;
    @FindBy(css = ".slick-track > li:nth-of-type(8)")
    private WebElement fourthPizzaInSlider;
    @FindBy(css = ".slick-track > li:nth-of-type(9)")
    private WebElement nextPizzaInSlider;
    @FindBy(className = "slick-next")
    private WebElement rightArrowBtn;
    @FindBy(className = "slick-prev")
    private WebElement leftArrowBtn;

    @FindBy(css = ".cart-contents")
    public WebElement cart;


    public NavigationPanel(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void waitForLoadingAllPizzasInSlider(){
        wait.until(driver -> fourthPizzaInSlider
                .isDisplayed());
    }

    public void clickRightArrowBtnInSlider(){
        new Actions(driver)
                .moveToElement(fourthPizzaInSlider)
                .perform();
        wait.until(driver -> rightArrowBtn
                .isDisplayed());
        rightArrowBtn.click();
    }

    public void clickLeftArrowBtnInSlider(){
        new Actions(driver)
                .moveToElement(fourthPizzaInSlider)
                .perform();
        wait.until(driver -> leftArrowBtn
                .isDisplayed());
        leftArrowBtn.click();
    }

    public String getNameFourthPizzaInSlider(){
        return fourthPizzaInSlider.getText();
    }

    public String getNameFirstPizzaInSlider(){
        return firstPizzaInSlider.getText();
    }

    public String getNameNextPizzaInSlider(){
        return fourthPizzaInSlider.getText();
    }

    public String getNamePreviousPizzaInSlider(){
        return previousPizzaInSlider.getText();
    }

    public void waitForLoadingNextPizza(){
        wait.until(driver -> nextPizzaInSlider
                .isDisplayed());
    }
}
