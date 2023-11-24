import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTests {

    @Test
    void Add_emptyString_Returns0(){
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    void Add_SingleValue_ReturnsValue(){
        assertEquals(19, StringCalculator.add("19"));
    }

    @Test
    void Add_TwoValues_ReturnsSum(){
        assertEquals(4, StringCalculator.add("1,3"));
    }

    @Test
    void Test_UseCustomDelimiter_ReturnsSum(){
        assertEquals(25, StringCalculator.add("//[l]\n3,3l19"));
    }

    @Test
    void Test_NegativeNumbers_ThrowsException(){
        assertThrows(RuntimeException.class, ()->StringCalculator.add("-1,-3,10"));
    }

    @Test
    void Test_NumbersGreaterThan1000AreIgnored(){
        assertEquals(1999, StringCalculator.add("1000,1001,999"));
    }

    @Test
    void Test_MultipleDelimitersAreSupported(){
        assertEquals(43, StringCalculator.add("//[h][:]\n3h5,21\n4:10"));
    }

    @Test
    void Test_MultipleDelimitersOfAnyLengthAreSupported(){
        assertEquals(34, StringCalculator.add("//[hello][world][!]\n3hello5,21\n4world1!0"));
    }

    //Edge cases
    @Test
    void Test_DelimitersOnly_Returns0() {
        assertEquals(0, StringCalculator.add("//[oops nothing to add]\n"));
    }

    /**
     * This implementation does not support using "]" as a delimiter, it is ignored instead.
     * It might be worth adding some sanitation on the delimiters to prevent using square brackets
     * at all.
     */
    @Test
    void Test_DelimitersIncludesSquareBracket_ReturnsSum() {
        assertEquals(5, StringCalculator.add("//[]][[][hello]\n4hello1"));
    }

    /**
     * Whilst not specified by the challenge, this implementation handles repeated delimiters
     * by ignoring them.
     */
    @Test
    void Test_EmptyNumbers() {
        assertEquals(10, StringCalculator.add(",\n3,5\n\n2"));
    }

}
