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

class MenuTest {
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
    void testingMenu() {
        String data = "convert";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "convert");
    }

    @Test
    void testingMenu2() {
        String data = "sum";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "sum");
    }

    @Test
    void testingMenu3() {
        String data = "exit";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "exit");
    }

    @Test
    void testingMenu4() {
        String data = "Google\nsum";
        InputStream stdin = System.in;

        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.input = new Scanner(System.in);

        assertEquals(App.menu(), "sum");
    }

}