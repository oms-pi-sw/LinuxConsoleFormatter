/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi;

import linuxconsoleformatter.ansi.utils.AnsiEsc;
import linuxconsoleformatter.ansi.format.AnsiColorExtendedType;
import linuxconsoleformatter.ansi.format.AnsiColor;
import linuxconsoleformatter.ansi.format.AnsiColorType;
import linuxconsoleformatter.ansi.format.AnsiAttribute;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import linuxconsoleformatter.ansi.cursor.AnsiCursor;
import linuxconsoleformatter.ansi.erase.EraseMode;
import linuxconsoleformatter.ansi.ttysettings.WrapMode;

/**
 *
 * @author root
 */
public class Ansi {

  private final StringBuilder ansi;
  private final Format format;
  private final Settings settings;
  private final Erase erase;
  private final Cursor cursor;

  private Ansi() {
    ansi = new StringBuilder("");
    format = new Format(this);
    settings = new Settings(this);
    erase = new Erase(this);
    cursor = new Cursor(this);
  }

  /**
   * Get ansi class instance.
   *
   * @return the Ansi new instance.
   */
  public static Ansi ansi() {
    return new Ansi();
  }

  public static void print(String str) {
    System.out.print(str);
  }

  public static void flush() {
    System.out.flush();
  }

  public static void println(String str) {
    System.out.println(str);
  }

  public static void println() {
    System.out.println();
  }

  public static void print(Ansi ansi) {
    System.out.print(ansi.toString());
    System.out.flush();
  }

  public static void println(Ansi ansi) {
    System.out.println(ansi.toString());
  }

  public Ansi resetTTY() {
    generateAnsiCode((Character) null, 'c', (ObservableList<Integer>) null);
    return this;
  }

  public Format format() {
    return format;
  }

  public Settings settings() {
    return settings;
  }

  public Erase erase() {
    return erase;
  }

  public Cursor cursor() {
    return cursor;
  }

  private abstract class AnsiCode {

    protected final Ansi ansiInstance;

    private AnsiCode(Ansi instance) {
      ansiInstance = instance;
    }
  }

  public class Format extends AnsiCode {

    private Format(Ansi instance) {
      super(instance);
    }

    /**
     * Append an Ansi attribute.
     *
     * @param a the attribute to append.
     * @return the Ansi with appended text.
     */
    public Ansi attribute(AnsiAttribute a) {
      if (a != null) {
        appendFormat(a.value());
      }
      return ansiInstance;
    }

    /**
     * Append bold ansi attribute.
     *
     * @return the Ansi with appended text.
     */
    public Ansi boldOn() {
      attribute(AnsiAttribute.INTENSITY_BOLD);
      return ansiInstance;
    }

    /**
     * Append bold off ansi attribute.
     *
     * @return the Ansi with appended text.
     */
    public Ansi boldOff() {
      attribute(AnsiAttribute.INTENSITY_BOLD_OFF);
      return ansiInstance;
    }

    /**
     * Append reset ansi attribute.
     *
     * @return the Ansi with appended text.
     */
    public Ansi reset() {
      appendFormat(AnsiAttribute.RESET.value());
      return ansiInstance;
    }

    /**
     * Append ansi foreground color.
     *
     * @param c the AnsiColor.
     * @return the Ansi with appended text.
     */
    public Ansi fg(AnsiColor c) {
      if (c != null) {
        appendFormat(c.fg());
      }
      return ansiInstance;
    }

    /**
     * Append ansi background color.
     *
     * @param c the AnsiColor.
     * @return
     */
    public Ansi bg(AnsiColor c) {
      if (c != null) {
        appendFormat(c.bg());
      }
      return ansiInstance;
    }

    /**
     * Append ansi foreground bright color.
     *
     * @param c the AnsiColor.
     * @return the Ansi with appended text.
     */
    public Ansi fgBright(AnsiColor c) {
      if (c != null) {
        appendFormat(c.fgBright());
      }
      return ansiInstance;
    }

    /**
     * Append ansi background bright color.
     *
     * @param c the AnsiColor.
     * @return the Ansi with appended text.
     */
    public Ansi bgBright(AnsiColor c) {
      if (c != null) {
        appendFormat(c.bgBright());
      }
      return ansiInstance;
    }

    /**
     * Append ansi 24-bit foreground color from a javafx Color.
     *
     * @param c the javafx.scene.paint.Color.
     * @return the Ansi with appended text.
     */
    public Ansi fgColor(javafx.scene.paint.Color c) {
      if (c != null) {
        extColor24bit(c, AnsiColorType.FOREGROUND);
      }
      return ansiInstance;
    }

    /**
     * Append ansi 24-bit background color from a javafx Color.
     *
     * @param c
     * @return the Ansi with appended text.
     */
    public Ansi bgColor(javafx.scene.paint.Color c) {
      if (c != null) {
        extColor24bit(c, AnsiColorType.BACKGROUND);
      }
      return ansiInstance;
    }

    /**
     * Append ansi 24-bit color from a javafx Color.
     *
     * @param c the javafx.scene.paint.Color.
     * @param type the AnsiColorType.
     * @return the Ansi with appended text.
     */
    public Ansi extColor24bit(javafx.scene.paint.Color c, AnsiColorType type) {
      if (c != null && type != null) {
        appendFormat(type.getType(),
                AnsiColorExtendedType.C24BIT.getType(),
                (int) (c.getRed() * 255),
                (int) (c.getGreen() * 255),
                (int) (c.getBlue() * 255));
      }
      return ansiInstance;
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
      if (type != null) {
        appendFormat(type.getType(),
                AnsiColorExtendedType.C8BIT.getType(),
                c);
      }
      return ansiInstance;
    }
  }

  public class Settings extends AnsiCode {

    public Settings(Ansi instance) {
      super(instance);
    }

    public Ansi setWrapMode(WrapMode mode) {
      if (mode != null) {
        generateAnsiCode(FXCollections.observableArrayList(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiEsc.SETTINGS_ESC_CHAR.getEsc()),
                FXCollections.observableArrayList(mode.getEnd()),
                FXCollections.observableArrayList(mode.getCode()));
      }
      return ansiInstance;
    }
  }

  public class Erase extends AnsiCode {

    public Erase(Ansi instance) {
      super(instance);
    }

    public Ansi eraseLine() {
      return setErase(EraseMode.LINE_ALL);
    }

    public Ansi setErase(EraseMode mode) {
      if (mode != null) {
        generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(),
                mode.getEnd(),
                FXCollections.observableArrayList(mode.getCode()));
      }
      return ansiInstance;
    }
  }

  public class Cursor extends AnsiCode {

    public Cursor(Ansi instance) {
      super(instance);
    }

    public Ansi save() {
      generateAnsiCode((Character) null, '7', null);
      return ansiInstance;
    }

    public Ansi load() {
      generateAnsiCode((Character) null, '8', null);
      return ansiInstance;
    }

    public Ansi up(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.UP.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    public Ansi down(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.DOWN.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    public Ansi forward(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.FORWARD.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    public Ansi backward(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.BACKWARD.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    public Ansi home(int row, int col) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.HOME.getEnd(), FXCollections.observableArrayList(row, col));
      return ansiInstance;
    }

    public Ansi force(int row, int col) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.FORCE.getEnd(), FXCollections.observableArrayList(row, col));
      return ansiInstance;
    }

    public Ansi saveAlt() {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.SAVE.getEnd(), null);
      return ansiInstance;
    }

    public Ansi loadAlt() {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.LOAD.getEnd(), null);
      return ansiInstance;
    }
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
    if (format != null) {
      appendFormat(FXCollections.observableArrayList(format));
    }
  }

  private void appendFormat(ObservableList<Integer> format) {
    generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiEsc.END_FORMAT.getEsc(), format);
  }

  private void generateAnsiCode(Character prefixChar, Character suffixChar, ObservableList<Integer> format) {
    generateAnsiCode(prefixChar != null ? FXCollections.observableArrayList(prefixChar) : null,
            suffixChar != null ? FXCollections.observableArrayList(suffixChar) : null,
            format);
  }

  private void generateAnsiCode(ObservableList<Character> prefixChars, ObservableList<Character> suffixChars, ObservableList<Integer> format) {
    ansi.append(AnsiEsc.ANSI_ESC_CHAR.getEsc());
    if (prefixChars != null && !prefixChars.isEmpty()) {
      prefixChars.forEach(_char -> ansi.append(_char));
    }
    if (format != null) {
      for (int i = 0; i < format.size(); i++) {
        ansi.append(format.get(i));
        if (i < (format.size() - 1)) {
          ansi.append(AnsiEsc.VALUE_SEPARATOR_CHAR);
        }
      }
    }
    if (suffixChars != null && !suffixChars.isEmpty()) {
      suffixChars.forEach(_char -> ansi.append(_char));
    }
  }

  @Override
  public String toString() {
    return ansi.toString();
  }
}
