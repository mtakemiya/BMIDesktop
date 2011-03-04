/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jp.atr.dni.bmi.desktop.timeline.model;

/**
 *
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・國際電氣通信基礎技術研究所]</i>
 *
 * @version 2011/03/03
 */
public class ViewerChannel {

   private boolean selected;

   private int depth;

   /**
    * @return the selected
    */
   public boolean isSelected() {
      return selected;
   }

   /**
    * @param selected the selected to set
    */
   public void setSelected(boolean selected) {
      this.selected = selected;
   }

   /**
    * @return the depth
    */
   public int getDepth() {
      return depth;
   }

   /**
    * @param depth the depth to set
    */
   public void setDepth(int depth) {
      this.depth = depth;
   }
}
