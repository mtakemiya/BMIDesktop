/**
 * 
 */
package jp.atr.dni.bmi.desktop.neuroshareutils;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Makoto Takemiya - [武宮 誠] <br />
 * <i>ATR - [株式会社・国際電気通信基礎技術研究所]</i>
 *
 * @version 2009/11/25
 */
@XStreamAlias("neuralInfo")
public class NeuralInfo extends Entity {
   private long sourceEntityID;

   private long sourceUnitID;

   private String probeInfo;

   private ArrayList<Double> data;

   /**
    * @param tag
    * @param entityInfo
    */
   public NeuralInfo(Tag tag, EntityInfo entityInfo) {
      super(tag, entityInfo);
   }

   /**
    * @param tag
    * @param entityInfo
    * @param sourceEntityID
    * @param sourceUnitID
    * @param probeInfo
    */
   public NeuralInfo(Tag tag, EntityInfo entityInfo, long sourceEntityID, long sourceUnitID, String probeInfo) {
      super(tag, entityInfo);

      if (probeInfo == null) {
         probeInfo = "";
      }

      this.sourceEntityID = sourceEntityID;
      this.sourceUnitID = sourceUnitID;
      this.probeInfo = probeInfo.trim();
   }

   /**
    * @return the sourceEntityID
    */
   public long getSourceEntityID() {
      return sourceEntityID;
   }

   /**
    * @param sourceEntityID the sourceEntityID to set
    */
   public void setSourceEntityID(long sourceEntityID) {
      this.sourceEntityID = sourceEntityID;
   }

   /**
    * @return the sourceUnitID
    */
   public long getSourceUnitID() {
      return sourceUnitID;
   }

   /**
    * @param sourceUnitID the sourceUnitID to set
    */
   public void setSourceUnitID(long sourceUnitID) {
      this.sourceUnitID = sourceUnitID;
   }

   /**
    * @return the probeInfo
    */
   public String getProbeInfo() {
      return probeInfo;
   }

   /**
    * @param probeInfo the probeInfo to set
    */
   public void setProbeInfo(String probeInfo) {
      this.probeInfo = probeInfo;
   }

   /**
    * @return the data
    */
   public ArrayList<Double> getData() {
      return data;
   }

   /**
    * @param data the data to set
    */
   public void setData(ArrayList<Double> data) {
      this.data = data;
   }
}