/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.ansi.format;

/**
 *
 * @author root
 */
public enum AnsiColorType {
  FOREGROUND(38),
  BACKGROUND(48);

  private final int type;

  private AnsiColorType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
