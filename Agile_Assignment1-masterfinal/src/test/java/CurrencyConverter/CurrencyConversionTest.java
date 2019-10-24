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

class CurrencyConversionTest {
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
    void testWrongCurrencyMessage() {
        String wrongCurrency = App.wrongCurrencyMessage("Yen");
        String wrongCurrencyMessage = "Yen is not a valid currency\nPlease enter again!";
        assertEquals(wrongCurrency, wrongCurrencyMessage);
    }

    @Test
    void testFromCurrency1() {
        String data = "usd";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.USD);
    }

    @Test
    void testFromCurrency2() {
        String data = "exit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), null);
    }

    @Test
    void testToCurrency1() {
        String data = "aud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), App.CurrenciesIndex.AUD);
    }

    @Test
    void testToCurrency2() {
        String data = "exit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(App.fromCurrency(), null);
    }

    @Test
    void testAmountToConvert() {
        App.CurrenciesIndex aud = App.CurrenciesIndex.AUD;
        App.CurrenciesIndex eur = App.CurrenciesIndex.EURO;
        String data = "100";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);
        assertEquals(100, App.amountToConvert(aud, eur));
    }

    @Test
    void testConvert() {
        App.CurrenciesIndex aud = App.CurrenciesIndex.AUD;
        App.CurrenciesIndex eur = App.CurrenciesIndex.EURO;
        assertEquals(62.0, App.convert(aud, eur, 100.0));
    }
}