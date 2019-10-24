package CurrencyConverter;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class whoAreYouTest {
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
  public void whoAreYouAdminTest(){

    String s = "admin";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("admin", App.whoAreYou());
  }

  @Test void whoAreYouUserTest(){
    String s = "user";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("user", App.whoAreYou());
  }

  @Test void whoAreYouInvalidInputTest(){
    String s = "I'm invalid\nI'm also invalid\nadmin";
    ByteArrayInputStream in = new ByteArrayInputStream(s.getBytes());
    System.setIn(in);

    App.input = new Scanner(System.in);
    assertEquals("admin", App.whoAreYou());
    assertEquals("Who are you? (ADMIN / USER)" + System.lineSeparator() +
        "Invalid input" + System.lineSeparator() +
        "Who are you? (ADMIN / USER)" + System.lineSeparator() +
        "Invalid input" + System.lineSeparator() +
        "Who are you? (ADMIN / USER)" + System.lineSeparator(), outContent.toString());
  }

}