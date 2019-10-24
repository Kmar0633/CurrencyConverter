package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserAccTest {
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
  public void userAccExitTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void userAccCheckRateTest(){
    String s = "check rate\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void userAccInvalidInputTest(){
    String s = "I'm Invalid\nI'm Invalid\nHELLLOOO\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void userAccCombinationTest(){
    String s = "check rate\nexit\nI'm Invalid\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
            "Invalid input" + System.lineSeparator() +
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator()
        ,
        outContent.toString());
  }

  @Test
   public void ifConditionsNullTest1(){
    String s = "check rate\nconvert\npgd\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
                    "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                    "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                    "Please select a valid currency!" + System.lineSeparator() +
                    "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator()+
                    "What do you want to do? (Check Rate / exit)" + System.lineSeparator()
            ,
            outContent.toString());
  }

  @Test
  public void ifConditionsNullTest2(){
    String s = "check rate\nconvert\naud\npgd\nexit\nexit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.userAcc();

    assertEquals(
            "What do you want to do? (Check Rate / exit)" + System.lineSeparator() +
                    "Would you like to convert or sum the money? Enter exit to exit." + System.lineSeparator() +
                    "Select From Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                    "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator() +
                    "Please select a valid currency!" + System.lineSeparator() +
                    "Select To Currency: (USD, AUD, EURO, POUND, SGD)[CASE INSENSITIVE] " + System.lineSeparator()+
                    "What do you want to do? (Check Rate / exit)" + System.lineSeparator()
            ,
            outContent.toString());
  }

}
