/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi.utils;

/**
 *
 * @author root
 */
public enum AnsiEsc {

  ANSI_ESC_CHAR((char) 27),
  SECOND_ESC_CHAR('['),
  SETTINGS_ESC_CHAR('?'),
  VALUE_SEPARATOR_CHAR(';'),
  END_FORMAT('m'),
  END_SCREEN('J'),
  END_LINE('K');

  private final Character esc;

  private AnsiEsc(Character esc) {
    this.esc = esc;
  }

  public Character getEsc() {
    return esc;
  }

  @Override
  public String toString() {
    return esc.toString();
  }
}
