package test_web.wework.page;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactPageTest {
    static MainPage main;
    static ContactPage contact;
    String name = "test2";
    String accid = "1";
    String mobile = "13355122113";

    @BeforeAll
    static void beforeAll(){
        main = new MainPage();
        contact = main.toContact();
    }

    @Test
    void testAddMember() {
        String user = contact.addMember(name, accid, mobile).search(name).getUserName();
        assertEquals(user, name);

    }

    @Test
    void testSearch(){
        contact.search(name).delete();
        contact.refresh();
    }

    @Test
    void testInputFromFile(){
        System.out.println(this.getClass().getResource("/module.xlsx"));
        contact.importFromFile(this.getClass().getResource("/module.xlsx"));
    }

    @AfterAll
    static void afterAll(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        main.quit();
    }
}