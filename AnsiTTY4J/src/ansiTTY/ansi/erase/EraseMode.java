/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi.erase;

import ansiTTY.ansi.utils.AnsiEsc;

/**
 *
 * @author root
 */
public enum EraseMode {

  /**
   * Erase line from cursor position to end.
   *
   */
  LINE_END(0, AnsiEsc.END_LINE.getEsc()),
  /**
   * Erase line from cursor position to start.
   *
   */
  LINE_START(1, AnsiEsc.END_LINE.getEsc()),
  /**
   * Erase all the line.
   *
   */
  LINE_ALL(2, AnsiEsc.END_LINE.getEsc()),
  /**
   * Erase screen from cursor position to end.
   *
   */
  SCREEN_END(0, AnsiEsc.END_SCREEN.getEsc()),
  /**
   * Erase screen from cursor position to start.
   *
   */
  SCREEN_START(1, AnsiEsc.END_SCREEN.getEsc()),
  /**
   * Erase all the screen.
   *
   */
  SCREEN_ALL(2, AnsiEsc.END_SCREEN.getEsc());

  private final int code;
  private final char end;

  private EraseMode(int code, char end) {
    this.code = code;
    this.end = end;
  }

  /**
   * Get the Ansi code.
   *
   * @return the Ansi code.
   */
  public int getCode() {
    return code;
  }

  /**
   * Get the end char.
   *
   * @return the end char.
   */
  public char getEnd() {
    return end;
  }
}
