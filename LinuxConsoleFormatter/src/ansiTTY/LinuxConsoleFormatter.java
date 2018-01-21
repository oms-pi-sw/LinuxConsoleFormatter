/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY;

import javafx.scene.paint.Color;
import ansiTTY.ansi.Ansi;
import static ansiTTY.ansi.Ansi.print;
import static ansiTTY.ansi.Ansi.println;
import ansiTTY.ansi.format.AnsiAttribute;
import ansiTTY.ansi.format.AnsiColor;
import ansiTTY.ansi.format.AnsiColorType;
import ansiTTY.utils.Utility;

/**
 *
 * @author root
 */
public class LinuxConsoleFormatter {

  /**
   * @param args the command line arguments
   * @throws Exception the generic exception.
   *
   */
  public static void main(String[] args) throws Exception {
    print(Ansi.ansi().resetTTY());

    println(Ansi.ansi().format().fgColor(Color.ROSYBROWN).format().bgColor(Color.CYAN).format().attribute(AnsiAttribute.INTENSITY_BOLD).a("RESET TTY").format().reset());

    println(Ansi.ansi().format().bgBright(ansiTTY.ansi.format.AnsiColor.GREEN).format().attribute(AnsiAttribute.ITALIC).a("TEST 1 FORMAT").format().reset());

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
      Thread.sleep(10);
    }
    Thread.sleep(50);
    println(Ansi.ansi().cursor().load().format().bgBright(AnsiColor.RED).a("COMPLETED!").format().reset().nl().erase().eraseLine().a("( ͡° ͜ʖ ͡°)"));

    println();

    Utility.initProgressBar("PROGRESS:");
    for (int i = 0; i < 100; i++) {
      int k = i + 1;
      progress = k;
      Utility.printProgressBar(progress, 15, true, '#', Color.RED, Color.BLUE, '_', null, Color.GREY, '.', '-', '=');
      Thread.sleep(100);
    }
    Thread.sleep(500);
    Utility.endProgressBar(Ansi.ansi().format().bg(AnsiColor.RED).a("COMPLETED!").format().reset());
  }

}
