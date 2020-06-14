package testcase;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AiceTest {
    public static WebDriver driver;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
    }

    @Test
    public void login(){
        driver.get("https://ceshiren.com/");
        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        driver.findElement(By.id("login-account-name")).sendKeys("ying.cao");
        driver.findElement(By.id("login-account-password")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();
        

    }
}


