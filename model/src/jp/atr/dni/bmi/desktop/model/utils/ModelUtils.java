package jp.atr.dni.bmi.desktop.model.utils;

import jp.atr.dni.bmi.desktop.model.api.ChannelType;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/17
 */
public class ModelUtils {

   public static ChannelType getChannelTypeFromLong(long type) {

      if (type == 1) {
         return ChannelType.EVENT;
      } else if (type == 2) {
         return ChannelType.ANALOG;
      } else if (type == 3) {
         return ChannelType.SEGMENT;
      } else if (type == 4) {
         return ChannelType.NEURAL_SPIKE;
      }

      return ChannelType.UNKNOWN;
   }
}
