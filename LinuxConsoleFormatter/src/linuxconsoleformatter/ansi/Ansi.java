/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi;

/**
 *
 * @author root
 */
public class Ansi {

  private static final char FIRST_ESC_CHAR = 27;
  private static final char SECOND_ESC_CHAR = '[';

  private final StringBuilder ansi;

  private Ansi() {
    ansi = new StringBuilder("");
  }

  public static Ansi ansi() {
    return new Ansi();
  }

  /**
   * NewLine function: append a new line.
   *
   * @return the Ansi with appended text.
   */
  public Ansi nl() {
    ansi.append(System.lineSeparator());
    return this;
  }

  /**
   * Append function.
   *
   * @param value the object to append to text.
   * @return the Ansi with appended text.
   */
  public Ansi a(Object value) {
    ansi.append(value);
    return this;
  }

  /**
   * Append function.
   *
   * @param value the string to append to text.
   * @return the Ansi with appended text.
   */
  public Ansi a(String value) {
    ansi.append(value);
    return this;
  }

}
