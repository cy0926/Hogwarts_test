package junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAssertion {
//    @Test
    void assertion(){

        assertAll("demo assertion",
                ()-> assertEquals(2,2),
                ()-> assertEquals(1,1),
                ()-> assertEquals(3,3)
        );
        System.out.println(("xxxx"));

    }
}
