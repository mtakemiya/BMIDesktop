/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model.api;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/22
 */
public enum ChannelType {

   /** Times for neural events. */
   NEURAL_SPIKE,
   /** Times and associated values for analog data. */
   ANALOG,
   /** Times and event labels. */
   EVENT,
   /** Times and associated values with segment ID. */
   SEGMENT,
   /** Other/Unknown */
   UNKNOWN;

   @Override
   public String toString() {
      if (this == NEURAL_SPIKE) {
         return "neural spike";
      } else if (this == ANALOG) {
         return "analog";
      } else if (this == EVENT) {
         return "event";
      } else if (this == SEGMENT) {
         return "segment";
      } else {
         return "unknown";
      }
   }
}
