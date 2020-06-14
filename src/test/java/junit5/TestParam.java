package junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TestParam {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testWithValueSource(int argument){
        assertTrue(argument >0 && argument <4);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument){
        assertNotNull(argument);
    }

    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String argument){
        assertNotNull(argument);
    }

    static Stream<String> testWithDefaultLocalMethodSource(){
        return Stream.of("apple", "banana", "orange");
    }

    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list){
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <= 2);
        assertEquals(2,list.size());
    }
    static Stream<Arguments> stringIntAndListProvider(){
        return Stream.of(
                Arguments.arguments("apple", 1, Arrays.asList("a", "b")),
                Arguments.arguments("lemon", 2, Arrays.asList("l", "m"))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "apple, 1",
            "banana, 2",
            "'lemon,lime',0xF1"
    })
    void testWithCsvSource(String fruit, int rank){
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSource(String country, int reference){
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

}
