/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.atr.dni.bmi.desktop.timeline.model;

import jp.atr.dni.bmi.desktop.model.ChannelType;
import jp.atr.dni.bmi.desktop.workingfileutils.TSData;

/**
 * 
 * @author makoto
 */
public class ViewerChannel {

   private boolean selected;
   private double sampleRate;
   private double normalizer;
   private double subtractor;
   private String label;
   private ChannelType channelType;
   private TSData tSData;

//   private int depth;
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
    * @return the channelType
    */
   public ChannelType getChannelType() {
      return channelType;
   }

   /**
    * @param channelType the channelType to set
    */
   public void setChannelType(ChannelType channelType) {
      this.channelType = channelType;
   }

   /**
    * @return the tSData
    */
   public TSData gettSData() {
      return tSData;
   }

   /**
    * @param tSData the tSData to set
    */
   public void settSData(TSData tSData) {
      this.tSData = tSData;
   }

   /**
    * @return the sampleRate
    */
   public double getSampleRate() {
      return sampleRate;
   }

   /**
    * @param sampleRate the sampleRate to set
    */
   public void setSampleRate(double sampleRate) {
      this.sampleRate = sampleRate;
   }

   /**
    * @return the normalizer
    */
   public double getNormalizer() {
      return normalizer;
   }

   /**
    * @param normalizer the normalizer to set
    */
   public void setNormalizer(double normalizer) {
      this.normalizer = normalizer;
   }

   /**
    * @return the subtractor
    */
   public double getSubtractor() {
      return subtractor;
   }

   /**
    * @param subtractor the subtractor to set
    */
   public void setSubtractor(double subtractor) {
      this.subtractor = subtractor;
   }

   /**
    * @return the label
    */
   public String getLabel() {
      return label;
   }

   /**
    * @param label the label to set
    */
   public void setLabel(String label) {
      this.label = label;
   }
}
