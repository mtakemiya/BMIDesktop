package jp.atr.dni.bmi.desktop.model.data;

import java.util.ArrayList;
import jp.atr.dni.bmi.desktop.model.APIList;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/11
 */
public class NSNTimeValData implements APIData {

   private double samplingRate;
   private APIList<Double> timeStamps;
   private ArrayList<APIList<Double>> values;

   public NSNTimeValData() {
//      timeStamps = new APIList<Double>(new NSNDataProvider());
      values = new ArrayList<APIList<Double>>();
   }
}
