/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import jp.atr.dni.bmi.desktop.model.ChannelType;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/06/27
 */
public class ModelUtils {

   public static ChannelType getChannelTypeFromLong(long type) {

      if (type == 1) {
         return ChannelType.TS_AND_LABEL;
      } else if (type == 2) {
         return ChannelType.TS_AND_VAL;
      } else if (type == 3) {
         return ChannelType.TS_AND_VAL_AND_ID;
      } else if (type == 4) {
         return ChannelType.TIMESTAMP;
      }

      return ChannelType.UNKNOWN;
   }
   
   public static FileNameExtensionFilter[] getDataFileFileters(){
       
       ArrayList<FileNameExtensionFilter> filters = new ArrayList<FileNameExtensionFilter>();
       filters.add(new FileNameExtensionFilter("ATR CSV (*.csv)", "csv"));
       filters.add(new FileNameExtensionFilter("BlackRockMicroSystems (*.nev, *.ns1,..., *.ns9)", "ns1", "ns2", "ns3", "ns4", "ns5", "ns6", "ns7", "ns8", "ns9", "nev"));
       filters.add(new FileNameExtensionFilter("Plexon (*.plx)", "plx"));
       filters.add(new FileNameExtensionFilter("Neuroshare (*.nsn)", "nsn"));
       
       return (FileNameExtensionFilter[])filters.toArray(new FileNameExtensionFilter[0]);
   }
   
}
