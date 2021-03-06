package test_web.wework.page;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;

public class ContactPage extends BasePage {
    By addMember = By.linkText("添加成员");
    By username = By.name("username");
    By accid = By.name("acctid");
    By mobile = By.name("mobile");
    By save = By.cssSelector(".js_btn_save");
    By delete = By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addMember(String name, String accid, String mobile){
        while (driver.findElements(username).size() == 0){
            click(addMember);
        }
        sendKeys(username, name);
        sendKeys(this.accid, accid);
        sendKeys(this.mobile, mobile);
        click(save);
        return this;
    }

    public ContactPage search(String keyword){
        sendKeys(By.id("memberSearchInput"), keyword);
        return this;
    }

    public String getUserName(){
        return getText(By.cssSelector(".member_display_cover_detail_name"));
    }

    public ContactPage delete(){
        click(delete);
        click(By.linkText("确认"));
        click(By.id("clearMemberSearchInput"));
        return this;
    }
    public ContactPage importFromFile(URL path){
        //todo:
        System.out.println(path.getPath());

        String path_utf="";
        try {
            path_utf=URLDecoder.decode(path.getFile(), "UTF-8");
            System.out.println(path_utf);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        click(By.cssSelector(".ww_operationBar:nth-child(1) .ww_btn_PartDropdown_left"));
        click(By.linkText("文件导入"));
//        click(By.name("file"));
//        sendKeys(By.name("file"), "C:\\fakepath\\module.xlsx");
        upload(By.name("file"), path_utf);
//        driver.findElement(By.name("file")).sendKeys("/Users/seveniruby/projects/Java3/src/main/resources/module.xlsx");
//        sendKeys(By.name("file"), "C:\\fakepath\\module.xlsx");
        click(By.linkText("确认导入"));
        click(By.linkText("前往查看"));

        return this;
    }

}
