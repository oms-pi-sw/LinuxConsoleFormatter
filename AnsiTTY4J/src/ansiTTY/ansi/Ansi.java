/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi;

import ansiTTY.ansi.utils.AnsiEsc;
import ansiTTY.ansi.format.AnsiColorExtendedType;
import ansiTTY.ansi.format.AnsiColor;
import ansiTTY.ansi.format.AnsiColorType;
import ansiTTY.ansi.format.AnsiAttribute;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ansiTTY.ansi.cursor.AnsiCursor;
import ansiTTY.ansi.erase.EraseMode;
import ansiTTY.ansi.settings.WrapMode;
import javafx.scene.paint.Color;

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

  /**
   * Print a string to console.
   *
   * @param str the string to print.
   */
  public static void print(String str) {
    System.out.print(str);
  }

  /**
   * Flush the console.
   */
  public static void flush() {
    System.out.flush();
  }

  /**
   * Print the string and a new line to console.
   *
   * @param str the string to print.
   */
  public static void println(String str) {
    System.out.println(str);
  }

  /**
   * Print new line to console.
   */
  public static void println() {
    System.out.println();
  }

  /**
   * Print an Ansi formatted string to console.
   *
   * @param ansi the Ansi string.
   */
  public static void print(Ansi ansi) {
    System.out.print(ansi.toString());
    System.out.flush();
  }

  /**
   * Print an Ansi formatted string and a new line to console.
   *
   * @param ansi the Ansi string.
   */
  public static void println(Ansi ansi) {
    System.out.println(ansi.toString());
  }

  /**
   * Reset tty.
   *
   * @return the Ansi with appended text.
   */
  public Ansi resetTTY() {
    generateAnsiCode((Character) null, 'c', (ObservableList<Integer>) null);
    return this;
  }

  /**
   * Start format manipulation.
   *
   * @return Format object with methods that returns Ansi formatted string
   */
  public Format format() {
    return format;
  }

  /**
   * Start TTY settings manipulation.
   *
   * @return Settings object with methods that returns Ansi string with TTY
   * settings.
   */
  public Settings settings() {
    return settings;
  }

  /**
   * Start erase line or screen manipulation.
   *
   * @return Erase object with methods that return Ansi string with erase
   * settings.
   */
  public Erase erase() {
    return erase;
  }

  /**
   * Start cursor position manipulation.
   *
   * @return Cursor object with methods that return Ansi string with cursor
   * position settings.
   */
  public Cursor cursor() {
    return cursor;
  }

  private abstract class AnsiCode {

    protected final Ansi ansiInstance;

    private AnsiCode(Ansi instance) {
      ansiInstance = instance;
    }
  }

  /**
   * Format manipulation class.
   */
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
     * @return the Ansi with appended text.
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
     * @param c the color code from 0 to 255.
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
     */
    public Ansi extColor8bit(int c, AnsiColorType type) {
      c = (Math.abs(c) & 0xFF);
      if (type != null) {
        appendFormat(type.getType(),
                AnsiColorExtendedType.C8BIT.getType(),
                c);
      }
      return ansiInstance;
    }
  }

  /**
   * Settings manipulation class.
   */
  public class Settings extends AnsiCode {

    private Settings(Ansi instance) {
      super(instance);
    }

    /**
     * Set line wrap mode.
     *
     * @param mode the line wrap mode spacs.
     * @return the Ansi with appended text.
     */
    public Ansi setWrapMode(WrapMode mode) {
      if (mode != null) {
        generateAnsiCode(FXCollections.observableArrayList(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiEsc.SETTINGS_ESC_CHAR.getEsc()),
                FXCollections.observableArrayList(mode.getEnd()),
                FXCollections.observableArrayList(mode.getCode()));
      }
      return ansiInstance;
    }
  }

  /**
   * Erase manipulation class.
   */
  public class Erase extends AnsiCode {

    private Erase(Ansi instance) {
      super(instance);
    }

    /**
     * Erase current line.
     *
     * @return the Ansi with appended text.
     */
    public Ansi eraseLine() {
      return setErase(EraseMode.LINE_ALL);
    }

    /**
     * Erase a line or a portion of the screen.
     *
     * @param mode the erase mode.
     * @return the Ansi with appended text.
     */
    public Ansi setErase(EraseMode mode) {
      if (mode != null) {
        generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(),
                mode.getEnd(),
                FXCollections.observableArrayList(mode.getCode()));
      }
      return ansiInstance;
    }
  }

  /**
   * Cursor manipulation class.
   */
  public class Cursor extends AnsiCode {

    private Cursor(Ansi instance) {
      super(instance);
    }

    /**
     * Save the cursor position.
     *
     * @return the Ansi with appended text.
     */
    public Ansi save() {
      generateAnsiCode((Character) null, '7', null);
      return ansiInstance;
    }

    /**
     * Load the cursor position.
     *
     * @return the Ansi with appended text.
     */
    public Ansi load() {
      generateAnsiCode((Character) null, '8', null);
      return ansiInstance;
    }

    /**
     * Moves the cursor up by <code>lines</code> rows
     *
     * @param lines the lines.
     * @return the Ansi with appended text.
     */
    public Ansi up(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.UP.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    /**
     * Moves the cursor down by <code>lines</code> rows
     *
     * @param lines the lines.
     * @return the Ansi with appended text.
     */
    public Ansi down(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.DOWN.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    /**
     * Moves the cursor forward by <code>lines</code> rows
     *
     * @param lines the lines.
     * @return the Ansi with appended text.
     */
    public Ansi forward(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.FORWARD.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    /**
     * Moves the cursor backward by <code>lines</code> rows
     *
     * @param lines the lines.
     * @return the Ansi with appended text.
     */
    public Ansi backward(int lines) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.BACKWARD.getEnd(), FXCollections.observableArrayList(lines));
      return ansiInstance;
    }

    /**
     * Sets the cursor position where subsequent text will begin.
     *
     * @param row the row.
     * @param col the column.
     * @return the Ansi with appended text.
     */
    public Ansi home(int row, int col) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.HOME.getEnd(), FXCollections.observableArrayList(row, col));
      return ansiInstance;
    }

    /**
     * Sets the cursor position where subsequent text will begin.
     *
     * @return the Ansi with appended text.
     */
    public Ansi home() {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.HOME.getEnd(), null);
      return ansiInstance;
    }

    /**
     * Identical to Cursor Home (<code>home(int, int)</code>).
     *
     * @param row the row.
     * @param col the column.
     * @return the Ansi with appended text.
     */
    public Ansi force(int row, int col) {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.FORCE.getEnd(), FXCollections.observableArrayList(row, col));
      return ansiInstance;
    }

    /**
     * Save the cursor position.
     *
     * @return the Ansi with appended text.
     */
    public Ansi saveAlt() {
      generateAnsiCode(AnsiEsc.SECOND_ESC_CHAR.getEsc(), AnsiCursor.SAVE.getEnd(), null);
      return ansiInstance;
    }

    /**
     * Load the cursor position.
     *
     * @return the Ansi with appended text.
     */
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

  private static final String START = "@{";
  private static final String END = "}@";

  private static final String FORMAT_N = "f";
  private static final String CURSOR_N = "f";
  private static final String ERASE_N = "e";

  private static final String RGB = "rgb";

  private static final String ARG_S = "(";
  private static final String ARG_E = ")";

  private static final String CODE_SEP = ",";
  private static final String RGB_SEP = ";";
  private static final String MANIPULATOR_SEP = ":";
  private static final String TEXT_SEP = " ";

  /**
   *
   * @param value
   * @return
   * @throws Exception
   */
  public Ansi parse(String value) throws Exception {
    if (occurencesCounter(value, START) != occurencesCounter(value, END)) {
      throw new Exception("PARSE ERROR");
    }
    _parse(value);
    return this;
  }

  private void _parse(String value) throws Exception {
    try {
      final String start_format = START + FORMAT_N + MANIPULATOR_SEP;

      int start_index = value.indexOf(start_format);
      int end_index = value.indexOf(END);
      if (start_index < 0 && end_index < 0) {
        //NO ANSI CODE
        a(value);
      } else if (start_index >= 0 && start_index < end_index) {
        //FOUND A START @{f:
        a(value.substring(0, start_index));

        //SEPARATING COMMANDS
        String command = value.substring(start_index + start_format.length());
        int command_end = command.indexOf(TEXT_SEP);

        String remaining = command.substring(command_end + TEXT_SEP.length());

        command = command.substring(0, command_end);

        //PARSING OF COMMANDS
        String[] commands = command.split(CODE_SEP);
        for (String c : commands) {
          if (c.startsWith(RGB)) {
            c = c.substring(RGB.length());
            try {
              c = c.substring(ARG_S.length(), c.length() - ARG_E.length());
            } catch (Exception ex) {
              throw new Exception("PARSE ERROR");
            }
            String[] rgb_values = c.split(RGB_SEP);
            boolean fg = true;
            switch (rgb_values.length) {
              case 3:
                break;
              case 4:
                if (rgb_values[3].equalsIgnoreCase("bg")) {
                  fg = false;
                }
                break;
              default:
                throw new Exception("PARSE ERROR");
            }
            try {
              Color color = Color.rgb(Integer.parseInt(rgb_values[0]),
                      Integer.parseInt(rgb_values[1]),
                      Integer.parseInt(rgb_values[2]));
              if (fg) {
                format().fgColor(color);
              } else {
                format().bgColor(color);
              }
            } catch (Exception ex) {
              throw new Exception("PARSE ERROR");
            }
          } else {
            boolean fg = true;
            final String bg = "bg_";
            if (c.toLowerCase().startsWith(bg)) {
              fg = false;
              c = c.substring(bg.length());
            }
            for (AnsiColor color : AnsiColor.values()) {
              if (color.toString().equalsIgnoreCase(c)) {
                if (fg) {
                  format().fg(color);
                } else {
                  format().bg(color);
                }
                break;
              }
            }
          }
        }

        _parse(remaining);
      } else if ((end_index >= 0 && start_index < 0) || (start_index > end_index)) {
        //FOUND AN END }@
        a(value.substring(0, end_index)).format().reset();
        _parse(value.substring(end_index + END.length()));
      } else if (end_index < 0 && start_index >= 0) {
        throw new Exception("PARSE ERROR.");
      } else {
        throw new Exception("PARSE ERROR.");
      }
    } catch (Exception ex) {
      throw ex;
    }
  }

  /**
   * Render the input string.
   *
   * @param value the string to parse.
   * @return the Ansi with appended text.
   * @throws java.lang.Exception the exception.
   */
  public Ansi render(String value) throws Exception {
    if (occurencesCounter(value, START) != occurencesCounter(value, END)) {
      throw new Exception("PARSE ERROR");
    }
    while (true) {
      int first_token = value.indexOf(START);
      int sub_str_start;
      a(value.substring(0, (sub_str_start = (first_token >= 0 ? first_token : (value.length())))));
      value = value.substring(first_token >= 0 ? sub_str_start + START.length() : value.length());
      if (value.isEmpty()) {
        break;
      } else if (first_token >= 0) {
        int man_len;
        if (value.substring(0, (man_len = FORMAT_N.length() + MANIPULATOR_SEP.length())).equals(FORMAT_N + MANIPULATOR_SEP)) {
          //FORMAT
          //REMOVE "f:"
          value = value.substring(man_len);
          int command_indexof = value.indexOf(TEXT_SEP);

          //SEPARATE COMMAND
          String command = value.substring(0, command_indexof);

          //FIND FIRST END
          value = value.substring(command_indexof + TEXT_SEP.length());
          int first_indexof_end = value.indexOf(END);

          //SEPARATE FORMATTED TEXT TO APPEND
          String text = value.substring(0, first_indexof_end);
          value = value.substring(first_indexof_end + END.length());

          String[] commands = command.split(CODE_SEP);
          for (String c : commands) {
            if (c.startsWith(RGB)) {
              c = c.substring(RGB.length());
              try {
                c = c.substring(ARG_S.length(), c.length() - ARG_E.length());
              } catch (Exception ex) {
                throw new Exception("PARSE ERROR");
              }
              String[] rgb_values = c.split(RGB_SEP);
              boolean fg = true;
              switch (rgb_values.length) {
                case 3:
                  break;
                case 4:
                  if (rgb_values[3].equalsIgnoreCase("bg")) {
                    fg = false;
                  }
                  break;
                default:
                  throw new Exception("PARSE ERROR");
              }
              try {
                Color color = Color.rgb(Integer.parseInt(rgb_values[0]),
                        Integer.parseInt(rgb_values[1]),
                        Integer.parseInt(rgb_values[2]));
                if (fg) {
                  format().fgColor(color);
                } else {
                  format().bgColor(color);
                }
              } catch (Exception ex) {
                throw new Exception("PARSE ERROR");
              }
            } else {
              boolean fg = true;
              final String bg = "bg_";
              if (c.toLowerCase().startsWith(bg)) {
                fg = false;
                c = c.substring(bg.length());
              }
              for (AnsiColor color : AnsiColor.values()) {
                if (color.toString().equalsIgnoreCase(c)) {
                  if (fg) {
                    format().fg(color);
                  } else {
                    format().bg(color);
                  }
                  break;
                }
              }
            }
          }

          a(text).format().reset();
        } else if (value.substring(0, ERASE_N.length() + MANIPULATOR_SEP.length()).equals(FORMAT_N + MANIPULATOR_SEP)) {
          //ERASE
          throw new Exception("NOT SUPPORTED PARSE MANIPULATOR YET");
        } else if (value.substring(0, CURSOR_N.length() + MANIPULATOR_SEP.length()).equals(FORMAT_N + MANIPULATOR_SEP)) {
          //CURSOR
          throw new Exception("NOT SUPPORTED PARSE MANIPULATOR YET");
        } else {
          throw new Exception("PARSE ERROR");
        }
      }
    }
    return this;
  }

  private int occurencesCounter(String value, String stringToFind) {
    int idx = 0, count = 0;
    while ((idx = value.indexOf(stringToFind, idx)) >= 0) {
      count++;
      idx += stringToFind.length();
    }
    return count;
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
