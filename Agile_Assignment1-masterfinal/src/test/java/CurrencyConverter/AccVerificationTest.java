package CurrencyConverter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
class AccVerificationTest {
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
  public void AccVerificationAdminTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.accVerification("admin");
    assertEquals(
        "What do you want to do? (Check Rate / Update Currency / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  @Test
  public void AccVerificationUserTest(){
    String s = "exit";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);
    App.input = new Scanner(System.in);

    App.accVerification("user");
    assertEquals(
        "What do you want to do? (Check Rate / exit)" + System.lineSeparator(),
        outContent.toString());
  }

  //There is no invalid input test as it gets filtered before reaching this method
}