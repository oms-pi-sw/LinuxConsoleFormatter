/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linuxconsoleformatter;

import javafx.scene.paint.Color;
import linuxconsoleformatter.ansi.Ansi;
import static linuxconsoleformatter.ansi.Ansi.print;
import static linuxconsoleformatter.ansi.Ansi.println;
import linuxconsoleformatter.ansi.format.AnsiAttribute;
import linuxconsoleformatter.ansi.format.AnsiColor;
import linuxconsoleformatter.ansi.format.AnsiColorType;

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
    print(Ansi.ansi().resetTTY());
    
    println(Ansi.ansi().format().fgColor(Color.ROSYBROWN).format().bgColor(Color.CYAN).format().attribute(AnsiAttribute.INTENSITY_BOLD).a("RESET TTY").format().reset());
    
    println(Ansi.ansi().format().bgBright(linuxconsoleformatter.ansi.format.AnsiColor.GREEN).format().attribute(AnsiAttribute.ITALIC).a("TEST 1 FORMAT").format().reset());
    
    println(Ansi.ansi().format().extColor8bit(0, AnsiColorType.FOREGROUND).a("TEST 2 FORMAT").format().reset());
    
    println();
    
    double progress;
    final int bar = 10;
    
    print(Ansi.ansi().a("PROGRESS: ").cursor().save());
    for (int i = 0; i < 100; i++) {
      int k = i + 1;
      progress = k;
      println(Ansi.ansi().cursor().load().cursor().save().a(progress).a("%"));
      int p = (int) Math.floor((double) k / (double) bar);
      print(Ansi.ansi().a("["));
      for (int j = 0; j < bar; j++) {
        if (j < p) {
          print(Ansi.ansi().a("#"));
        } else {
          print(Ansi.ansi().a("-"));
        }
      }
      print(Ansi.ansi().a("]"));
      Thread.sleep(100);
    }
    Thread.sleep(500);
    println(Ansi.ansi().cursor().load().format().bgBright(AnsiColor.RED).a("COMPLETED!").format().reset().nl().erase().eraseLine().a("( ͡° ͜ʖ ͡°)"));
  }
  
}
