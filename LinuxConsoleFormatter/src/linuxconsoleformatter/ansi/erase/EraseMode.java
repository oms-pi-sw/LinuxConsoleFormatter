/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi.erase;

import linuxconsoleformatter.ansi.utils.AnsiEsc;

/**
 *
 * @author root
 */
public enum EraseMode {
  LINE_END(0, AnsiEsc.END_LINE.getEsc()),
  LINE_START(1, AnsiEsc.END_LINE.getEsc()),
  LINE_ALL(2, AnsiEsc.END_LINE.getEsc()),
  SCREEN_END(0, AnsiEsc.END_SCREEN.getEsc()),
  SCREEN_START(1, AnsiEsc.END_SCREEN.getEsc()),
  SCREEN_ALL(2, AnsiEsc.END_SCREEN.getEsc());

  private final int code;
  private final char end;

  private EraseMode(int code, char end) {
    this.code = code;
    this.end = end;
  }

  public int getCode() {
    return code;
  }

  public char getEnd() {
    return end;
  }
}
