/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter.ansi.format;

/**
 *
 * @author root
 */
public enum AnsiAttribute {
  RESET(0, "RESET"),
  INTENSITY_BOLD(1, "INTENSITY_BOLD"),
  INTENSITY_FAINT(2, "INTENSITY_FAINT"),
  ITALIC(3, "ITALIC_ON"),
  UNDERLINE(4, "UNDERLINE_ON"),
  BLINK_SLOW(5, "BLINK_SLOW"),
  BLINK_FAST(6, "BLINK_FAST"),
  NEGATIVE_ON(7, "NEGATIVE_ON"),
  CONCEAL_ON(8, "CONCEAL_ON"),
  STRIKETHROUGH_ON(9, "STRIKETHROUGH_ON"),
  UNDERLINE_DOUBLE(21, "UNDERLINE_DOUBLE"),
  INTENSITY_BOLD_OFF(22, "INTENSITY_BOLD_OFF"),
  ITALIC_OFF(23, "ITALIC_OFF"),
  UNDERLINE_OFF(24, "UNDERLINE_OFF"),
  BLINK_OFF(25, "BLINK_OFF"),
  NEGATIVE_OFF(27, "NEGATIVE_OFF"),
  CONCEAL_OFF(28, "CONCEAL_OFF"),
  STRIKETHROUGH_OFF(29, "STRIKETHROUGH_OFF");

  private final int value;
  private final String name;

  AnsiAttribute(int index, String name) {
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
}
