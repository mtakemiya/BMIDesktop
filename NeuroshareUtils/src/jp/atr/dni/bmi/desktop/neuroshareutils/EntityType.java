/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

/**
 *
 * @author Keiji Harada [*1]</br>[*1] ATR Intl. Conputational Neuroscience Labs, Decoding Group
 * @version 2011/04/22
 */
public enum EntityType {

   /**
    *
    */
   ENTITY_EVENT,
   /**
    * 
    */
   ENTITY_ANALOG,
   /**
    *
    */
   ENTITY_SEGMENT,
   /**
    * 
    */
   ENTITY_NEURAL,
   /**
    *
    */
   INFO_FILE,
   /**
    * 
    */
   UNKNOWN;

   /**
    *
    * @param identifier
    * @return
    */
   public static EntityType getEntityType(long nsnIdentifier) {
      if (nsnIdentifier == 1) {
         return EntityType.ENTITY_EVENT;
      } else if (nsnIdentifier == 2) {
         return EntityType.ENTITY_ANALOG;
      } else if (nsnIdentifier == 3) {
         return EntityType.ENTITY_SEGMENT;
      } else if (nsnIdentifier == 4) {
         return EntityType.ENTITY_NEURAL;
      } else if (nsnIdentifier == 5) {
         return EntityType.INFO_FILE;
      } else {
         return EntityType.UNKNOWN;
      }
   }
}
