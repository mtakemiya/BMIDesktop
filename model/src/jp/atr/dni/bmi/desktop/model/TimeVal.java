/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・國際電氣通信基礎技術研究所]</i>
 *
 * @version 2010/10/29
 */
class TimeVal {

   private long time;
   private double value;

   /**
    * @return the time
    */
   public long getTime() {
      return time;
   }

   /**
    * @param time the time to set
    */
   public void setTime(long time) {
      this.time = time;
   }

   /**
    * @return the value
    */
   public double getValue() {
      return value;
   }

   /**
    * @param value the value to set
    */
   public void setValue(double value) {
      this.value = value;
   }
}