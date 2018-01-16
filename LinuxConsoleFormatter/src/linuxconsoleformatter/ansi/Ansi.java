/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi;

import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author root
 */
public class Ansi {

  private static final char FIRST_ESC_CHAR = 27;
  private static final char SECOND_ESC_CHAR = '[';
  private static final char RGB_VALUE_SEPARATOR_ESC_CHAR = ';';
  private static final char END_ESC_CHAR = 'm';

  private final StringBuilder ansi;

  private Ansi() {
    ansi = new StringBuilder("");
  }

  /**
   * Get ansi class instance.
   *
   * @return the Ansi new instance.
   */
  public static Ansi ansi() {
    return new Ansi();
  }

  /**
   * Append an Ansi attribute.
   *
   * @param a the attribute to append.
   * @return the Ansi with appended text.
   */
  public Ansi attribute(AnsiAttribute a) {
    appendFormat(a.value());
    return this;
  }

  /**
   * Append bold ansi attribute.
   *
   * @return the Ansi with appended text.
   */
  public Ansi boldOn() {
    attribute(AnsiAttribute.INTENSITY_BOLD);
    return this;
  }

  /**
   * Append bold off ansi attribute.
   *
   * @return the Ansi with appended text.
   */
  public Ansi boldOff() {
    attribute(AnsiAttribute.INTENSITY_BOLD_OFF);
    return this;
  }

  /**
   * Append reset ansi attribute.
   *
   * @return the Ansi with appended text.
   */
  public Ansi reset() {
    appendFormat(AnsiAttribute.RESET.value());
    return this;
  }

  /**
   * Append ansi foreground color.
   *
   * @param c the AnsiColor.
   * @return the Ansi with appended text.
   */
  public Ansi fg(AnsiColor c) {
    appendFormat(c.fg());
    return this;
  }

  /**
   * Append ansi background color.
   *
   * @param c the AnsiColor.
   * @return
   */
  public Ansi bg(AnsiColor c) {
    appendFormat(c.bg());
    return this;
  }

  /**
   * Append ansi foreground bright color.
   *
   * @param c the AnsiColor.
   * @return the Ansi with appended text.
   */
  public Ansi fgBright(AnsiColor c) {
    appendFormat(c.fgBright());
    return this;
  }

  /**
   * Append ansi background bright color.
   *
   * @param c the AnsiColor.
   * @return the Ansi with appended text.
   */
  public Ansi bgBright(AnsiColor c) {
    appendFormat(c.bgBright());
    return this;
  }

  /**
   * Append ansi 24-bit foreground color from a javafx Color.
   *
   * @param c the javafx.scene.paint.Color.
   * @return the Ansi with appended text.
   */
  public Ansi fgColor(javafx.scene.paint.Color c) {
    extColor24bit(c, AnsiColorType.FOREGROUND);
    return this;
  }

  /**
   * Append ansi 24-bit background color from a javafx Color.
   *
   * @param c
   * @return the Ansi with appended text.
   */
  public Ansi bgColor(javafx.scene.paint.Color c) {
    extColor24bit(c, AnsiColorType.BACKGROUND);
    return this;
  }

  /**
   * Append ansi 24-bit color from a javafx Color.
   *
   * @param c the javafx.scene.paint.Color.
   * @param type the AnsiColorType.
   * @return the Ansi with appended text.
   */
  public Ansi extColor24bit(javafx.scene.paint.Color c, AnsiColorType type) {
    appendFormat(type.getType(),
            AnsiColorExtendedType.C24BIT.getType(),
            (int) (c.getRed() * 255),
            (int) (c.getGreen() * 255),
            (int) (c.getBlue() * 255));
    return this;
  }

  /**
   * Append ansi 8-bit color from a 8-bit integer value Color.
   *
   * @param c the int value of the color. Must be between 0 and 255.
   * @param type the AnsiColorType.
   * @return the Ansi with appended text.
   * @throws Exception
   */
  public Ansi extColor8bit(int c, AnsiColorType type) throws Exception {
    c = (Math.abs(c) & 0xFF);
    appendFormat(type.getType(),
            AnsiColorExtendedType.C8BIT.getType(),
            c);
    return this;
  }

  /**
   * Append system file separator.
   *
   * @return the Ansi with appended text.
   */
  public Ansi fileSeparator() {
    ansi.append(File.separator);
    return this;
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

  private void appendFormat(Integer... format) {
    appendFormat(FXCollections.observableArrayList(format));
  }

  private void appendFormat(ObservableList<Integer> format) {
    ansi.append(FIRST_ESC_CHAR).append(SECOND_ESC_CHAR);
    for (int i = 0; i < format.size(); i++) {
      ansi.append(format.get(i));
      if (i < (format.size() - 1)) {
        ansi.append(RGB_VALUE_SEPARATOR_ESC_CHAR);
      }
    }
    ansi.append(END_ESC_CHAR);
  }

  @Override
  public String toString() {
    return ansi.toString();
  }
}
