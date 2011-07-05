/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import com.sun.org.apache.bcel.internal.classfile.Unknown;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/05
 */
public enum FileType {

   NSN, PLX, NEV, NSX, CSV, DIRECTORY, UNKNOWN;

   public static FileType fromString(String str) {
      if (str.equalsIgnoreCase(".nsn")) {
         return NSN;
      } else if (str.equalsIgnoreCase(".plx")) {
         return PLX;
      } else if (str.equalsIgnoreCase(".nev")) {
         return NEV;
      } else if (str.matches(".ns[\\d]+")) {
         return NSX;
      } else if (str.equalsIgnoreCase(".csv")) {
         return CSV;
      } else {
         return UNKNOWN;
      }
   }

   public static FileType fromFileName(String fileName) {
      if (fileName == null || !fileName.contains(".")) {
         return UNKNOWN;
      }
      return fromString(fileName.substring(fileName.lastIndexOf('.')));
   }

   @Override
   public String toString() {
      if (this == NSN) {
         return "neuroshare";
      } else if (this == PLX) {
         return "plexon";
      } else if (this == NEV) {
         return "blackrock nev";
      } else if (this == NSX) {
         return "blackrock nsX";
      } else if (this == CSV) {
         return "comma-separated values";
      } else {
         return "unknown";
      }
   }

   public static boolean isDataFile(FileType type) {
      return type == NSN || type == PLX || type == NEV || type == NSX || type == CSV;
   }
}
