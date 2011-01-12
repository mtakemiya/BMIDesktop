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
public class DWordEventData extends EventData {
   
   /** To store 4-byte unsigned values in a signed type (like Java types) you have to use 8 bytes */
   private Long data;

   /**
    * Default constructor.
    * 
    * @param dTimestamp
    * @param dwDataByteSize
    */
   public DWordEventData(double dTimestamp, long dwDataByteSize) {
      super(dTimestamp, dwDataByteSize);
   }

   /**
    * @param dTimestamp
    * @param dwDataByteSize
    * @param data
    */
   public DWordEventData(double dTimestamp, long dwDataByteSize, Long data) {
      super(dTimestamp, dwDataByteSize);
      this.data = data;
   }

   /**
    * @return the data
    */
   public Long getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(Long data) {
      this.data = data;
   }
}