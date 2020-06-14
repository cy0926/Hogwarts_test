package testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.SimpleFormatter;

public class TestSelenium {
    public static WebDriver driver;

    @BeforeAll
    public static void setUp(){
        ChromeOptions options = new ChromeOptions();
        //浏览器复用
        options.setExperimentalOption("debuggerAddress","127.0.0.1:9222");
        driver = new ChromeDriver(options);  // 复用浏览器的时候才打开
        saveCookie(driver);
//        driver = new ChromeDriver();
    }

    @AfterAll
    public static void tearDown(){
        driver.quit();

    }

    @Test
    public void testDemo(){
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        loadCookie(driver);
        driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
        driver.findElement(new By.ById(("menu_contacts"))).click();
    }
    public static void saveCookie(WebDriver driver){
        //每次只取一条cookie进行处理
        try {
            FileWriter fileWriter = new FileWriter("cookie.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Cookie cookie: driver.manage().getCookies()){
                bufferedWriter.write(cookie.getName() + ';' +
                        cookie.getValue() + ';' +
                        cookie.getDomain() + ';' +
                        cookie.getPath() + ';' +
                        cookie.getExpiry() + ';' +
                        cookie.isSecure() );
                bufferedWriter.newLine();
            }
                bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void loadCookie(WebDriver driver){
        try {
            FileReader fileReader = new FileReader("cookie.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine())!= null){
                StringTokenizer tokenizer = new StringTokenizer(line, ";");
                String name = tokenizer.nextToken();
                String value = tokenizer.nextToken();
                String domain = tokenizer.nextToken();
                String path = tokenizer.nextToken();
                Date expiry = null;
                String dt = tokenizer.nextToken();
                if (!dt.equals("null")){
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
                    //把 String转成Date
                    expiry = sdf.parse(dt);
                }
                // 把 String 转成 boolean
                boolean isSecure = Boolean.parseBoolean(tokenizer.nextToken());
                Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
                driver.manage().addCookie(cookie);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
