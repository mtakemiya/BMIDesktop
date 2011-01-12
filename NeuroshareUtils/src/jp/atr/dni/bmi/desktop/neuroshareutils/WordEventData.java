/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 * 
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・国際電気通信基礎技術研究所]</i>
 *
 * @version 2009/11/17
 */
public class WordEventData extends EventData {

   private int data;

   public WordEventData(double dTimestamp, long dwDataByteSize) {
      super(dTimestamp, dwDataByteSize);
   }

   /**
    * @param dTimestamp
    * @param dwDataByteSize
    * @param data
    */
   public WordEventData(double dTimestamp, long dwDataByteSize, int data) {
      super(dTimestamp, dwDataByteSize);
      this.data = data;
   }

   /**
    * @return the data
    */
   public int getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(int data) {
      this.data = data;
   }
}