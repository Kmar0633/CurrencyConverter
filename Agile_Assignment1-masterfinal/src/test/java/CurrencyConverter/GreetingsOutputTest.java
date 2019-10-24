package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GreetingsOutputTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void before(){
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void after(){
        System.setIn(System.in);
        System.setOut(originalOut);
    }

    @Test
    void testShowGreeting() {
        String greeting1 = App.showGreeting();
        String greeting2 =
                "********************************************************\n"
                        + "********************************************************\n"
                        + "****************** Currency Converter ******************\n"
                        + "********************************************************\n"
                        + "********************************************************\n";
        assertEquals(greeting1, greeting2);
    }

    @Test
    void testShowExitGreeting() {
        String exit1 = App.showExitGreeting();
        String exit2 =
                "********************************************************\n"
                        + "********************************************************\n"
                        + "************************* BYE **************************\n"
                        + "********************************************************\n"
                        + "********************************************************\n";
        assertEquals(exit1, exit2);
    }
}