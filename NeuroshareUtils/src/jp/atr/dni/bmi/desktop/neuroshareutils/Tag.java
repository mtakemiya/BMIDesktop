package jp.atr.dni.bmi.desktop.neuroshareutils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author kharada
 * @version 2011/01/13
 */
@XStreamAlias("tag")
public class Tag {

   private EntityType entityType;
   private long elemLength;

   /**
    * Default constructor.
    */
   public Tag() {
      super();
   }

   /**
    * @param elemType
    * @param elemLength
    */
   public Tag(EntityType entityType, long elemLength) {
      super();
      this.entityType = entityType;
      this.elemLength = elemLength;
   }

   /**
    * @return the elemLength
    */
   public long getElemLength() {
      return elemLength;
   }

   /**
    * @param elemLength the elemLength to set
    */
   public void setElemLength(long elemLength) {
      this.elemLength = elemLength;
   }

   /**
    * @return the entityType
    */
   public EntityType getEntityType() {
      return entityType;
   }

   /**
    * @param entityType the entityType to set
    */
   public void setEntityType(EntityType entityType) {
      this.entityType = entityType;
   }
}
