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
public enum AnsiColor {
  BLACK(0, "BLACK"),
  RED(1, "RED"),
  GREEN(2, "GREEN"),
  YELLOW(3, "YELLOW"),
  BLUE(4, "BLUE"),
  MAGENTA(5, "MAGENTA"),
  CYAN(6, "CYAN"),
  WHITE(7, "WHITE"),
  DEFAULT(9, "DEFAULT");

  private final int value;
  private final String name;

  AnsiColor(int index, String name) {
    this.value = index;
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }

  public int value() {
    return value;
  }

  public int fg() {
    return value + 30;
  }

  public int bg() {
    return value + 40;
  }

  public int fgBright() {
    return value + 90;
  }

  public int bgBright() {
    return value + 100;
  }
}
