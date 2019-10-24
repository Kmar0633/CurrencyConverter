package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class AdminAccTest {
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
  public void adminAccExitTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccCheckRateTest(){
    String s = "check rate\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator()+
        "Would you like to convert or sum the money? Enter exit to exit."  + System.lineSeparator()+
            "What do you want to do? (Check Rate / Update Currency / exit)"+ System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccUpdateCurrencyTest(){
    String s = "update currency\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccInvalidInputTest(){
    String s = "I'm Invalid\nI'm Invalid\nHELLLOOO\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void adminAccCombinationTest(){
    String s = "check rate\nexit\nI'm Invalid\nupdate currency\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
            "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE]" + System.lineSeparator() +
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator()
        ,
        outContent.toString());
  }

  @Test
  public void adminAccExitAfterCheckRate(){
    String s = "check rate\nconvert\nusd\naud\n1\nn\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.adminAcc();

    assertEquals(
            "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator() +
                    "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                    "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                    "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                    "How much USD you want to convert to AUD?" + System.lineSeparator() +
                    "1.00 USD is 1.46 AUD!\n" + System.lineSeparator() +
                    "Would you like to make another conversion or Sum? Y or N" + System.lineSeparator() +
                    "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
            outContent.toString());
  }
  

}
