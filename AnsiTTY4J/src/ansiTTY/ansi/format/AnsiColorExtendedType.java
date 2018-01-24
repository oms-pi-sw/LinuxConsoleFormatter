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
public enum AnsiColorExtendedType {
  C8BIT(5),
  C24BIT(2);

  private final int type;

  private AnsiColorExtendedType(int type) {
    this.type = type;
  }

  public int getType() {
    return type;
  }
}
