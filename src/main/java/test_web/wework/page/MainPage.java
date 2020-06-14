package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import test_web.wework.util.WebCookies;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainPage extends BasePage{
    public static String cookiePath;

    public MainPage() {
        super();
        cookiePath = "cookie.txt";
        String url = "https://work.weixin.qq.com/wework_admin/frame#index";

        driver.get(url);
        driver.manage().deleteAllCookies();
        WebCookies.loadCookie(driver);
        driver.get(url);
        driver.manage().window().maximize();

    }

    public ContactPage toContact(){
        click(By.id("menu_contacts"));
        return new ContactPage(driver);
    }
}
