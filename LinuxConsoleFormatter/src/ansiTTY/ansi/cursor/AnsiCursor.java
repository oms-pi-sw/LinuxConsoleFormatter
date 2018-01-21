/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi.cursor;

/**
 *
 * @author root
 */
public enum AnsiCursor {

  /**
   * Cursor move to home position.
   *
   */
  HOME('H'),
  /**
   * Cursor move up.
   *
   */
  UP('A'),
  /**
   * Cursor move down.
   *
   */
  DOWN('B'),
  /**
   * Cursor move forward.
   *
   */
  FORWARD('C'),
  /**
   * Cursor move backward.
   *
   */
  BACKWARD('D'),
  /**
   * Identical to <code>HOME</code>.
   *
   */
  FORCE('f'),
  /**
   * Save cursor position.
   *
   */
  SAVE('s'),
  /**
   * Load cursor position.
   *
   */
  LOAD('u');

  private final char end;

  private AnsiCursor(char end) {
    this.end = end;
  }

  /**
   * Get end char.
   *
   * @return the end char.
   */
  public char getEnd() {
    return end;
  }
}
