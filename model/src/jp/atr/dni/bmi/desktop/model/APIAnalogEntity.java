/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.model;

import jp.atr.dni.bmi.desktop.neuroshareutils.AnalogInfo;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <a href="http://www.atr.jp">ATR - [株式会社・国際電氣通信基礎技術研究所]</a>
 *
 * @version 2011/07/07
 */
public class APIAnalogEntity implements APIEntity {

   private AnalogInfo nsnEntity;

   @Override
   public String getLabel() {
      return nsnEntity.getEntityInfo().getEntityLabel();
   }
}
