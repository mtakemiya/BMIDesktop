package jp.atr.dni.bmi.desktop.neuroshareutils;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・国際電気通信基礎技術研究所]</i>
 *
 * @version 2009/11/17
 */
@XStreamAlias("tag")
public class Tag {
   private ElemType elemType;

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
   public Tag(ElemType elemType, long elemLength) {
      super();
      this.elemType = elemType;
      this.elemLength = elemLength;
   }

   /**
    * @return the elemType
    */
   public ElemType getElemType() {
      return elemType;
   }

   /**
    * @param elemType the elemType to set
    */
   public void setElemType(ElemType elemType) {
      this.elemType = elemType;
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

}