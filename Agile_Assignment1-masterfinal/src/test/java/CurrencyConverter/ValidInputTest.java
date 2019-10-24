package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ValidInputTest {
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
    void validInputTest1(){
        String s = "0\n-1\nhello\n1";
        ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
        System.setIn(in);

        App.input = new Scanner(System.in);
        assertEquals(1, App.validInputTester());
    }
}