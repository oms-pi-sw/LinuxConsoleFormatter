/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi.settings;

/**
 *
 * @author root
 */
public enum WrapMode {
  ON(7, 'l'),
  OFF(7, 'h');

  private final int code;
  private final char end;

  private WrapMode(int code, char end) {
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
