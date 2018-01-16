/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter;

import javafx.scene.paint.Color;
import linuxconsoleformatter.ansi.Ansi;
import linuxconsoleformatter.ansi.AnsiAttribute;
import linuxconsoleformatter.ansi.AnsiColorType;

/**
 *
 * @author root
 */
public class LinuxConsoleFormatter {

  /**
   * @param args the command line arguments
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {
    System.out.println(Ansi.ansi().fgColor(Color.ROSYBROWN).bgColor(Color.CYAN).attribute(AnsiAttribute.INTENSITY_BOLD).a("e_ciao").reset().toString());
    System.out.println(Ansi.ansi().bgBright(linuxconsoleformatter.ansi.AnsiColor.GREEN).attribute(AnsiAttribute.ITALIC).a("i_ciao").reset().toString());
    System.out.println(Ansi.ansi().extColor8bit(0, AnsiColorType.FOREGROUND).a("8_ciao").reset().toString());
  }

}
