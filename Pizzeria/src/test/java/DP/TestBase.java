package DP;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public String initialWindow;
    public Set<String> getAllWindows(){
        return driver.getWindowHandles();
    };

    public void switchToFirstNewWindow(){
        var newWindow = getAllWindows().stream().filter(w -> !w.equals(initialWindow)).collect(Collectors.toSet());
        driver.switchTo().window(newWindow.stream().findFirst().get());
        //driver.switchTo().window(otherWindow.iterator().next());
    }

    public void switchToWindow(String windowId){
        driver.switchTo().window(windowId);
    }

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        var options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        initialWindow = driver.getWindowHandle();
    }

    @AfterEach
    public void tearDown() throws IOException {
        try{
            takeScreenshot();
        } catch (UnhandledAlertException alertException){
            Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept();
            takeScreenshot();
        }
        driver.quit();
    }

    private void takeScreenshot() throws IOException{
        var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("c:\\tmp\\screenshot.png"));
    }
}
