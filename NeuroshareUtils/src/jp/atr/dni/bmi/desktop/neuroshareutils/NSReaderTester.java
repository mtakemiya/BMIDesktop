/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 * 
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・國際電氣通信基礎技術研究所]</i>
 *
 * @version 2010/03/19
 */
public class NSReaderTester {
   /*
    * @param args
    */
   public static void main(String[] args) {

      NSReader reader = new NSReader();
      NeuroshareFile nsn = reader.readNSFile("test1.nsn", false);
   }
}