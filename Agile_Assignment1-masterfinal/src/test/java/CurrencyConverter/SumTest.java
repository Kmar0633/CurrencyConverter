package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SumTest {
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
    void testSum() {
        String data1 = "4\n-1\n3\nusd 1\neuro 1\npound\npound 1\naud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.sum();

        assertEquals(
                "How many currencies you want to sum up? "
                        + System.lineSeparator()
                        + "The maximum number you can sum is 3\nPlease enter again"
                        + System.lineSeparator()
                        + "You have entered an invalid number\nPlease try again"
                        + System.lineSeparator()
                        + "Enter the 1 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the 2 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the 3 currency and the amount"
                        + System.lineSeparator()
                        + "Invalid input, Please provide with a Currency and Amount!"
                        + System.lineSeparator()
                        + "Enter the 3 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the currency you would like to convert to:\nSelect To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Answer is 4.87"
                        + System.getProperty("line.separator"),
                outContent.toString());
    }

    @Test
    void testSum2() {
        String data1 = "word\n2\nYen\nYen 100\nusd 100\neuro -1\neuro amount\neuro 100\naud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.sum();

        assertEquals(
                "How many currencies you want to sum up? "
                        + System.lineSeparator()
                        + "You must input a number\nPlease try again"
                        + System.lineSeparator()
                        + "Enter the 1 currency and the amount"
                        + System.lineSeparator()
                        + "Invalid input, Please provide with a Currency and Amount!"
                        + System.lineSeparator()
                        + "Enter the 1 currency and the amount"
                        + System.lineSeparator()
                        + "Please select a valid currency!"
                        + System.lineSeparator()
                        + "Yen is not a valid currency\nPlease enter again!"
                        + System.lineSeparator()
                        + "Enter the 1 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the 2 currency and the amount"
                        + System.lineSeparator()
                        + "Invalid Amount given"
                        + System.lineSeparator()
                        + "Enter the 2 currency and the amount"
                        + System.lineSeparator()
                        + "input must be a number"
                        + System.lineSeparator()
                        + "Enter the 2 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the currency you would like to convert to:\nSelect To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Answer is 307.0"
                        + System.getProperty("line.separator"),
                outContent.toString());
    }

    @Test
    void testSum3() {
        String data1 = "0\n4\n-1\n3\nusd 1\neuro 1\npound\npound 1\naud";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        App.sum();

        assertEquals("How many currencies you want to sum up? "
                        + System.lineSeparator()
                        + "You have entered an invalid number\nPlease try again"
                        + System.lineSeparator()
                        + "The maximum number you can sum is 3\nPlease enter again"
                        + System.lineSeparator()
                        + "You have entered an invalid number\nPlease try again"
                        + System.lineSeparator()
                        + "Enter the 1 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the 2 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the 3 currency and the amount"
                        + System.lineSeparator()
                        + "Invalid input, Please provide with a Currency and Amount!"
                        + System.lineSeparator()
                        + "Enter the 3 currency and the amount"
                        + System.lineSeparator()
                        + "Enter the currency you would like to convert to:\nSelect To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] "
                        + System.lineSeparator()
                        + "Answer is 4.87"
                        + System.getProperty("line.separator"),
                outContent.toString());
    }

    @Test
    void testSum4() {
        String data1 = "1\nusd 1\nexit";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data1.getBytes()));
        App.input = new Scanner(System.in);
        assertNull(App.sum());
    }

    @Test
    void testSumHelper() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 10.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.EURO, 10.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.POUND, 10.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 48.7;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper2() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 0.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.EURO, 0.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.SGD, 0.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.AUD);
        double sum2 = 0.0;
        assertEquals(sum1, sum2);
    }

    @Test
    void testSumHelper3() {
        List<Pair<App.CurrenciesIndex, Double>> sumList = new ArrayList<>();
        sumList.add(new Pair<>(App.CurrenciesIndex.USD, 1000.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.AUD, 1000.0));
        sumList.add(new Pair<>(App.CurrenciesIndex.POUND, 1000.0));
        double sum1 = App.sumHelper(sumList, App.CurrenciesIndex.EURO);
        double sum2 = 2640;
        assertEquals(sum1, sum2);
    }

}
