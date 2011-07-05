/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/06/27
 */
public enum ChannelType {

   /** Times for neural events. */
   TIMESTAMP,
   /** Times and associated values for analog data. */
   TS_AND_VAL,
   /** Times and event labels. */
   TS_AND_LABEL,
   /** Times and associated values with segment ID. */
   TS_AND_VAL_AND_ID,
   /** Other/Unknown */
   UNKNOWN;

   @Override
   public String toString() {
      if (this == TIMESTAMP) {
         return "timestamps";
      } else if (this == TS_AND_VAL) {
         return "timestamps, values";
      } else if (this == TS_AND_LABEL) {
         return "timestamps, labels";
      } else if (this == TS_AND_VAL_AND_ID) {
         return "timestamps, values, segment IDs";
      } else {
         return "unknown";
      }
   }
}
