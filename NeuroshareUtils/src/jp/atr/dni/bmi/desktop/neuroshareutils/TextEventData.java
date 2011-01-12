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
public class TextEventData extends EventData {
   private String data;

   /**
    * @param dTimestamp
    * @param dwDataByteSize
    */
   public TextEventData(double dTimestamp, long dwDataByteSize) {
      super(dTimestamp, dwDataByteSize);
   }

   /**
    * @param data
    */
   public TextEventData(double dTimestamp, long dwDataByteSize, String data) {
      super(dTimestamp, dwDataByteSize);
      this.data = data;
   }

   /**
    * @return the data
    */
   public String getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(String data) {
      this.data = data;
   }
}