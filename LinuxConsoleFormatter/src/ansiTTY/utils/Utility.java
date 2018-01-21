/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ansiTTY.utils;

import ansiTTY.ansi.Ansi;
import static ansiTTY.ansi.Ansi.flush;
import static ansiTTY.ansi.Ansi.print;
import static ansiTTY.ansi.Ansi.println;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

/**
 *
 * @author root
 */
public class Utility {

  /**
   * Initialize progress bar
   *
   * @param title the title of progress bar.
   */
  public static void initProgressBar(final String title) {
    if (title != null) {
      println(Ansi.ansi().a(title));
    }
    print(Ansi.ansi().cursor().save());
    flush();
  }

  /**
   * Print progress bar.
   *
   * @param progress the progress (double from 0 to 100).
   * @param cols the progress bar width.
   * @param printPerc if true print progress percentage near progress bar.
   * @param completeChar the char to print on completed progress.
   * @param cfg the foreground color for <code>completeChar</code>
   * @param cbg the background color for <code>completeChar</code>
   * @param incompleteChar the char to print on remaining incomplete progress
   * @param ufg the foreground color for <code>incompleteChar</code>
   * @param ubg the background color for <code>incompleteChar</code>
   * @param intermediateChars the list of chars to print before
   * <code>completeChar</code>
   */
  public static void printProgressBar(final double progress, final int cols, final boolean printPerc,
          final char completeChar, final Color cfg, final Color cbg,
          final char incompleteChar, final Color ufg, final Color ubg,
          final Character... intermediateChars) {
    final double maxProgress = 100;
    final ObservableList<Character> chars = intermediateChars != null ? FXCollections.observableArrayList(intermediateChars) : null;
    Character _c = null;
    if (chars != null && !chars.isEmpty()) {
      double unit = maxProgress / cols;
      double part = unit / chars.size();
      double pos = progress % unit;
      int idx = (int) Math.floor(pos / part);
      _c = pos == 0 ? completeChar : chars.get(idx);
    }
    print(Ansi.ansi().cursor().load().erase().eraseLine().a("["));
    final int p = (int) Math.floor(progress * cols / maxProgress);
    for (int i = 0; i < cols; i++) {
      if (i < p) {
        print(Ansi.ansi().format().bgColor(cbg).format().fgColor(cfg).a(completeChar).format().reset());
      } else if (_c == null || i > p) {
        print(Ansi.ansi().format().bgColor(ubg).format().fgColor(ufg).a(incompleteChar).format().reset());
      } else {
        print(Ansi.ansi().format().bgColor(ubg).format().fgColor(cfg).a(_c).format().reset());
      }
    }
    print(Ansi.ansi().a("]"));
    if (printPerc) {
      print(Ansi.ansi().a(" [").a(progress).a("%]"));
    }
  }

  /**
   * End progress bar.
   *
   * @param message the end message.
   */
  public static void endProgressBar(final Ansi message) {
    println(Ansi.ansi().cursor().load().erase().eraseLine().a(message).format().reset());
  }
}
