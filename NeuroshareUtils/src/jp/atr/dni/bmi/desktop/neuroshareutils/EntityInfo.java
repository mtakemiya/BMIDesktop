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
public class EntityInfo {

   private String entityLabel;

   private long entityType;

   private long itemCount;

   /**
    * Default constructor.
    */
   public EntityInfo() {
      super();
   }

   /**
    * @param entityLabel
    * @param entityType
    * @param itemCount
    */
   public EntityInfo(String entityLabel, long entityType, long itemCount) {
      super();
      if (entityLabel == null) {
         entityLabel = "";
      }
      this.entityLabel = entityLabel.trim();
      this.entityType = entityType;
      this.itemCount = itemCount;
   }

   /**
    * @return the entityLabel
    */
   public String getEntityLabel() {
      return entityLabel;
   }

   /**
    * @param entityLabel the entityLabel to set
    */
   public void setEntityLabel(String entityLabel) {
      this.entityLabel = entityLabel;
   }

   /**
    * @return the entityType
    */
   public long getEntityType() {
      return entityType;
   }

   /**
    * @param entityType the entityType to set
    */
   public void setEntityType(long entityType) {
      this.entityType = entityType;
   }

   /**
    * @return the itemCount
    */
   public long getItemCount() {
      return itemCount;
   }

   /**
    * @param itemCount the itemCount to set
    */
   public void setItemCount(long itemCount) {
      this.itemCount = itemCount;
   }

}
