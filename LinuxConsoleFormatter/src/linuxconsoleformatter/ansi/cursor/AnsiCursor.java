/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi.cursor;

/**
 *
 * @author root
 */
public enum AnsiCursor {
  HOME('H'),
  UP('A'),
  DOWN('B'),
  FORWARD('C'),
  BACKWARD('D'),
  FORCE('f'),
  SAVE('s'),
  LOAD('u');

  private final char end;

  private AnsiCursor(char end) {
    this.end = end;
  }

  public char getEnd() {
    return end;
  }
}
