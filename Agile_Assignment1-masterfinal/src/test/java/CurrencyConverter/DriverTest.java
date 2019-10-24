package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
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
        // aud to usd
    void testingDriver() {
        String data1 = "convert\naud\nusd\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals(
                "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator()
                        + "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "How much AUD you want to convert to USD?"
                        + System.lineSeparator()
                        + "10.00 AUD is 6.90 USD!\n"
                        + System.lineSeparator()
                        + "Would you like to make another conversion or Sum? Y or N"
                        + System.lineSeparator(),
                outContent.toString());
    }

    @Test // aud to euro
    void testingDriver2() {
        String data1 = "convert\naud\neuro\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals(
                "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator()
                        + "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "How much AUD you want to convert to EURO?"
                        + System.lineSeparator()
                        + "10.00 AUD is 6.20 EURO!\n"
                        + System.lineSeparator()
                        + "Would you like to make another conversion or Sum? Y or N"
                        + System.lineSeparator(),
                outContent.toString());
    }

    @Test // aud to pound
    void testingDriver3() {
        String data1 = "convert\naud\npound\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals(
                "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator()
                        + "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "How much AUD you want to convert to POUND?"
                        + System.lineSeparator()
                        + "10.00 AUD is 5.60 POUND!\n"
                        + System.lineSeparator()
                        + "Would you like to make another conversion or Sum? Y or N"
                        + System.lineSeparator(),
                outContent.toString());
    }

    @Test // aud to sgd
    void testingDriver4() {
        String data1 = "convert\naud\nsgd\n10\nN";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();
        assertEquals(
                "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator()
                        + "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "How much AUD you want to convert to SGD?"
                        + System.lineSeparator()
                        + "10.00 AUD is 9.50 SGD!\n"
                        + System.lineSeparator()
                        + "Would you like to make another conversion or Sum? Y or N"
                        + System.lineSeparator(),
                outContent.toString());
    }

    @Test // aud to sgd
    void testingDriverExit() {
        String data1 = "convert\naud\nsgd\n10\nY\nexit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.driver();

        assertEquals(
                "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator()
                        + "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "How much AUD you want to convert to SGD?"
                        + System.lineSeparator()
                        + "10.00 AUD is 9.50 SGD!\n"
                        + System.lineSeparator()
                        + "Would you like to make another conversion or Sum? Y or N"
                        + System.lineSeparator()
                        + "Would you like to convert or sum the money? Enter exit to exit."
                        + System.lineSeparator(),
                outContent.toString());
    }

}