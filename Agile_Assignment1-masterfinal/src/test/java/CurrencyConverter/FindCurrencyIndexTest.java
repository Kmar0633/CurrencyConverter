package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FindCurrencyIndexTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void before() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void after() {
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    @Test
    void testingFindCurrencyIndex1() {
        assertEquals(App.findIndex("USD").toString(), "USD");
    }

    @Test
    void testingFindCurrencyIndex2() {
        assertEquals(App.findIndex("aud").toString(), "AUD");
    }

    @Test
    void testingFindCurrencyIndex3() {
        assertEquals(App.findIndex("SgD").toString(), "SGD");
    }

    /*
           Testing Enum get ID method
    */
    @Test
    void testingFindCurrencyIndex4() {
        assertEquals(App.findIndex("EURO").getIdx(), 2);
    }

    /*
           Testing wrong currency input
    */
    @Test
    void testingFindCurrencyIndex5() {
        assertNull(App.findIndex("INR"));
    }

}